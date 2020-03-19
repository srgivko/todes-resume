package by.sivko.resume.entities;

import by.sivko.resume.convertors.GenderConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "resume")
@NoArgsConstructor
@Data
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    private Date dob;

    @Convert(converter = GenderConverter.class)
    private Gender gender;

    @ManyToMany
    @JoinTable(
            name = "resume_technology",
            joinColumns = {@JoinColumn(name = "resume_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "technology_id", referencedColumnName = "id")})
    private Set<Technology> technologies;

    @OneToMany(mappedBy = "resume")
    private Set<Contact> contacts;
}
