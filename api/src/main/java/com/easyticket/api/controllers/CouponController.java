package com.easyticket.api.controllers;

import com.easyticket.api.domain.coupon.Coupon;
import com.easyticket.api.domain.coupon.CouponRequestDTO;
import com.easyticket.api.services.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @PostMapping("/event/{eventId}")
    public ResponseEntity<Coupon> addCouponsToEvent(
            @PathVariable UUID eventId,
            @RequestBody CouponRequestDTO data
    ) {
        Coupon coupons = couponService.addCouponToEvent(eventId, data);
        return ResponseEntity.ok(coupons);
    }
}
