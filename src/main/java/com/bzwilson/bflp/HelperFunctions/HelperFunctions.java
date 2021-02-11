package com.bzwilson.bflp.HelperFunctions;


import com.bzwilson.bflp.exceptions.ResourceNotFoundException;
import com.bzwilson.bflp.models.Customer;
import com.bzwilson.bflp.models.Freelancer;
import com.bzwilson.bflp.repositories.CustomerRepo;
import com.bzwilson.bflp.repositories.FreelancerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;


/**
 * Class contains helper functions - functions that are needed throughout the application. The class can be autowired
 * into any class.
 */
@Component
public class HelperFunctions {

    @Autowired
    private FreelancerRepo freelancerRepo;

    @Autowired
    private CustomerRepo customerRepo;

    /**
     * Checks to see if the authenticated user has access to modify the requested user's information
     *
     * @param username The user name of the user whose data is requested to be changed. This should either match the authenticated user
     *                 or the authenticate must have the role ADMIN
     * @return true if the user can make the modifications, otherwise an exception is thrown
     */
    public boolean isAuthorizedToMakeChange(String username) {
        // Check to see if the user whose information being requested is the current user
        // Check to see if the requesting user is an admin
        // if either is true, return true
        // otherwise stop the process and throw an exception
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        if (username.equalsIgnoreCase(authentication.getName()
                .toLowerCase()) || authentication.getAuthorities()
                .contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            // this user can make this change
            return true;
        } else {
            // stop user is not authorized to make this change so stop the whole process and throw an exception
            throw new ResourceNotFoundException(authentication.getName() + " not authorized to make change");
        }
    }


    public boolean customerUserNameisAvailable(String username) {
        Freelancer freelancer = freelancerRepo.findByUsername(username);

        return freelancer == null;
    }


    public boolean freelancerUserNameisAvailable(String username) {
        Customer customer = customerRepo.findByUsername(username);

        return customer == null;
    }

        public static byte[] compress(byte[] data) throws IOException {
            Deflater deflater = new Deflater();
            deflater.setInput(data);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
            deflater.finish();
            byte[] buffer = new byte[1024];
            while (!deflater.finished()) {
                int count = deflater.deflate(buffer); // returns the generated code... index
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
            byte[] output = outputStream.toByteArray();
            System.out.println("Original: " + data.length / 1024 + " Kb");
            System.out.println("Compressed: " + output.length / 1024 + " Kb");
            return output;
        }


        public static byte[] decompress(byte[] data) throws IOException, DataFormatException {
            Inflater inflater = new Inflater();
            inflater.setInput(data);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
            byte[] buffer = new byte[1024];
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }

            outputStream.close();
            byte[] output = outputStream.toByteArray();
            System.out.println("Original: " + data.length);
            System.out.println("Compressed: " + output.length);
            return output;
        }



//    public byte[] decompressBytes(byte[] data) {
//        Inflater inflater = new Inflater();
//        inflater.setInput(data);
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
//        byte[] buffer = new byte[1024];
//        try {
//            while (!inflater.finished()) {
//                int count = inflater.inflate(buffer);
//                outputStream.write(buffer, 0, count);
//            }
//            outputStream.close();
//        } catch (IOException ioe) {
//        } catch (DataFormatException e) {
//        }
//
//        System.out.println("uncompressed Image Byte Size - " + outputStream.toByteArray().length);
//        return outputStream.toByteArray();
//
//    }
//
//
//    public byte[] compressBytes(byte[] data) {
//        Deflater deflater = new Deflater();
//        deflater.setInput(data);
//        deflater.finish();
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
//        byte[] buffer = new byte[1024];
//        while (!deflater.finished()) {
//            int count = deflater.deflate(buffer);
//            outputStream.write(buffer, 0, count);
//        }
//        try {
//            outputStream.close();
//        } catch (IOException e) {
//
//        }
//        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
//        return outputStream.toByteArray();
//
//    }

//    /**
//     * Searches to see if the exception has any constraint violations to report
//     *
//     * @param cause the exception to search
//     * @return constraint violations formatted for sending to the client
//     */
//    public List<ValidationError> getConstraintViolation(Throwable cause)
//    {
//        // Find any data violations that might be associated with the error and report them
//        // data validations get wrapped in other exceptions as we work through the Spring
//        // exception chain. Hence we have to search the entire Spring Exception Stack
//        // to see if we have any violation constraints.
//        while ((cause != null) && !(cause instanceof ConstraintViolationException))
//        {
//            cause = cause.getCause();
//        }
//
//        List<ValidationError> listVE = new ArrayList<>();
//
//        // we know that cause either null or an instance of ConstraintViolationException
//        if (cause != null)
//        {
//            ConstraintViolationException ex = (ConstraintViolationException) cause;
//            for (ConstraintViolation cv : ex.getConstraintViolations())
//            {
//                ValidationError newVe = new ValidationError();
//                newVe.setCode(cv.getInvalidValue()
//                                      .toString());
//                newVe.setMessage(cv.getMessage());
//                listVE.add(newVe);
//            }
//        }
//        return listVE;
//  }
}
