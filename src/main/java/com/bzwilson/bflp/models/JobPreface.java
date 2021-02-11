import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;




// need a staging area for negotiantions and finalizations and what not

//    @Entity
//    @Table(name = "jobPre")
//    public class JobPreface {
//
//        @Id
//        @GeneratedValue(strategy = GenerationType.AUTO)
//        private long id;
//
//
//        @NotNull
//        @Column(nullable = false)
//        private String title;
//        private String username;
//
//
//
//
////    @OneToOne(cascade = CascadeType.ALL)
////    @JsonIgnoreProperties(value = "freelancer")
////    @JoinColumn(name = "image")
////    private FreelancerImageModel image;
//
//        @Lob
//        @Column
//        private byte[] picByte;