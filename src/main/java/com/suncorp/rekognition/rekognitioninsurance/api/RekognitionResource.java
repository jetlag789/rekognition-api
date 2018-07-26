////////////////////////////////////////////////////////////////////////////////
//
// Copyright (c) 2018, Suncorp Metway Limited. All rights reserved.
//
// This is unpublished proprietary source code of Suncorp Metway Limited.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
////////////////////////////////////////////////////////////////////////////////

package com.suncorp.rekognition.rekognitioninsurance.api;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class RekognitionResource
{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public RekognitionResource() {

    }

    @GetMapping(value = "/recognition")
    public ResponseEntity<RekognitionResponse> hello(@Valid @ModelAttribute RekognitionRequest rekognitionRequest) {
        logger.info("Request: " + rekognitionRequest);

//        Hello hello = dependentApi.hello(recognitionRequest.getName(), recognitionRequest.getTitle());
//        HelloResponseDTO helloResponseDTO = new HelloResponseDTO(hello);

        RekognitionResponse rekognitionResponse = null;

        rekognitionResponse = new RekognitionResponse();
        rekognitionResponse.setAge("20");

        ResponseEntity<RekognitionResponse> responseEntity = new ResponseEntity<>(rekognitionResponse, HttpStatus.OK);

        logger.info("Response: " + responseEntity);

        return responseEntity;
    }
}
