////////////////////////////////////////////////////////////////////////////////
//
// Copyright (c) 2018, Suncorp Metway Limited. All rights reserved.
//
// This is unpublished proprietary source code of Suncorp Metway Limited.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
////////////////////////////////////////////////////////////////////////////////
package com.suncorp.rekognition.rekognitioninsurance.service;

import org.junit.Test;

public class RekognitionServiceTest
{
    @Test
    public void shouldName() throws Exception
    {
        RekognitionService service = new RekognitionService();
        service.getFaceDetails();
    }
}