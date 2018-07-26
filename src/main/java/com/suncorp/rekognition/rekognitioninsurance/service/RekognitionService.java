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

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.AmazonRekognitionException;
import com.amazonaws.services.rekognition.model.DetectFacesRequest;
import com.amazonaws.services.rekognition.model.DetectFacesResult;
import com.amazonaws.services.rekognition.model.FaceDetail;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.util.IOUtils;

public class RekognitionService
{
    private AmazonRekognition client;
    private AWSCredentials credentials;

    public void getFaceDetails() {
        String photo = "photo1.jpg";
        String bucket = "bucket";


//        AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.defaultClient();

        try {
            credentials = new ProfileCredentialsProvider().getCredentials();
        } catch (Exception e) {
            throw new AmazonClientException("Cannot load the credentials from the credential profiles file. "
                                                    + "Please make sure that your credentials file is at the correct "
                                                    + "location (/Usersuserid.aws/credentials), and is in a valid format.", e);
        }

        ByteBuffer imageBytes = null;
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(photo)) {
            imageBytes = ByteBuffer.wrap(IOUtils.toByteArray(inputStream));
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }


        setupClient();
        displayFaceDetail(imageBytes, photo);
    }

    private void setupClient()
    {
        client = AmazonRekognitionClientBuilder
                .standard()
                .withRegion(Regions.AP_SOUTHEAST_2)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }

    private void displayFaceDetail(ByteBuffer imageBytes, String photo) {
        DetectFacesRequest dfRequest = new DetectFacesRequest()
                .withImage(new Image()
                                   .withBytes(imageBytes))
                .withAttributes("ALL");

        try {

            DetectFacesResult result = client.detectFaces(dfRequest);
            List<FaceDetail> faceDetails = result.getFaceDetails();

            System.out.println("Detected Face for " + photo);
            for (FaceDetail facedetail: faceDetails) {
                System.out.println("Face Detail: " + facedetail.toString());
            }

        } catch (AmazonRekognitionException e) {
            e.printStackTrace();
        }
    }
}
