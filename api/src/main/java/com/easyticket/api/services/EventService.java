package com.easyticket.api.services;

import com.easyticket.api.domain.event.Event;
import com.easyticket.api.domain.event.EventRequestDTO;
import com.easyticket.api.domain.event.EventResponseDTO;
import com.easyticket.api.repositories.EventRepository;
import com.easyticket.api.util.date.DateProvider;
import com.easyticket.api.util.validator.StringValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.nio.ByteBuffer;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class EventService {


    @Value("${aws.bucket.name}")
    private String bucketName;

    private final S3Client s3Client;
    private final EventRepository repository;
    private final StringValidator stringValidator;
    private final DateProvider dateProvider;
    private final AddressService addressService;

    public EventService(EventRepository repository, AddressService addressService, S3Client s3Client,
                        StringValidator stringValidator, DateProvider dateProvider) {
        this.repository = repository;
        this.addressService = addressService;
        this.s3Client = s3Client;
        this.stringValidator = stringValidator;
        this.dateProvider = dateProvider;
    }

    public Event createEvent(EventRequestDTO data) {
        String imgUrl = null;

        if (data.image() != null) {
            imgUrl = this.uploadImg(data.image());
        }

        Event newEvent = new Event();
        newEvent.setTitle(data.title());
        newEvent.setDescription(data.description());
        newEvent.setEventUrl(data.eventUrl());
        newEvent.setDate(new Date(data.date()));
        newEvent.setImgUrl(imgUrl);
        newEvent.setRemote(data.remote());

        repository.save(newEvent);

        if (!data.remote()) {
            this.addressService.createAddress(data, newEvent);
        }

        return newEvent;
    }

    public List<EventResponseDTO> getUpcomingEvents(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Event> eventsPage = this.repository.findUpcomingEvents(new Date(), pageable);
        return EventResponseDTO.pageToDTOList(eventsPage);
    }

    public List<EventResponseDTO> getFilteredEvents(
            int page, int size,  String title, String city, String uf, Date startDate, Date endDate
    ){
        title = stringValidator.validate(title, "");
        city = stringValidator.validate(city, "");
        uf = stringValidator.validate(uf, "");

        startDate = dateProvider.getStartDate(startDate);
        endDate = dateProvider.getEndDate(endDate);

        Pageable pageable = PageRequest.of(page, size);
        Page<Event> eventsPage = this.repository.findFilteredEvents(title, city, uf, startDate, endDate, pageable);
        return EventResponseDTO.pageToDTOList(eventsPage);
    }

    private String uploadImg(MultipartFile multipartFile) {
        String filename = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();

        try {
            PutObjectRequest putOb = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(filename)
                    .build();
            s3Client.putObject(putOb, RequestBody.fromByteBuffer(ByteBuffer.wrap(multipartFile.getBytes())));
            GetUrlRequest request = GetUrlRequest.builder()
                    .bucket(bucketName)
                    .key(filename)
                    .build();

            return s3Client.utilities().getUrl(request).toString();
        } catch (Exception e) {
            System.out.println("Erro ao upar arquivo!");
            System.out.println(e.getMessage());
            return "";
        }
    }

}
