package com.stafforbit.stafforbit.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "employees")
public class Employee 
{
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @NotBlank(message = "First name is required")
	    @Size(max = 100)
	    private String firstName;

	    @Size(max = 100)
	    private String lastName;

	    @Email(message = "Invalid email")
	    @Column(unique = true)
	    private String email;

	    private String department;

	    public Employee() {}

	    public Employee(String firstName, String lastName, String email, String department) {
	        this.firstName = firstName;
	        this.lastName = lastName;
	        this.email = email;
	        this.department = department;
	    }

	    // Getters and setters
	    public Long getId() { return id; }
	    public void setId(Long id) { this.id = id; }

	    public String getFirstName() { return firstName; }
	    public void setFirstName(String firstName) { this.firstName = firstName; }

	    public String getLastName() { return lastName; }
	    public void setLastName(String lastName) { this.lastName = lastName; }

	    public String getEmail() { return email; }
	    public void setEmail(String email) { this.email = email; }

	    public String getDepartment() { return department; }
	    public void setDepartment(String department) { this.department = department; }
}
