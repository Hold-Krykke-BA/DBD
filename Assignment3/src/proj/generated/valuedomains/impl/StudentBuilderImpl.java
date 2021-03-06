package proj.generated.valuedomains.impl;

import org.abstractica.edma.valuedomains.IValueInstance;
import org.abstractica.edma.valuedomains.exceptions.InvalidValueException;
import proj.generated.valuedomains.Age;
import proj.generated.valuedomains.Name;
import proj.generated.valuedomains.Student;
import proj.generated.valuedomains.Student.StudentBuilderAge;
import proj.generated.valuedomains.Student.StudentBuilderName;
import proj.generated.valuedomains.impl.AgeImpl;
import proj.generated.valuedomains.impl.NameImpl;

/**
 * 
 */
public class StudentBuilderImpl implements StudentBuilderAge, StudentBuilderName
{
    private Object[] edma_value;



    /**
     * Constructor
     */
    public StudentBuilderImpl()
    {
        edma_value = new Object[2];
    }

    /**
     * sets the name field.
     * @param name  The value to assign to the name field
     * @return      Builder interface for setting the age field
     */
    public StudentBuilderAge name(Name name)
    {
        if(name == null) throw new NullPointerException("The field name in Student may not be null");
        edma_value[0] = ((IValueInstance) name).edma_getValue();
        return this;
    }

    /**
     * sets the name field.
     * @param name  The value to assign to the name field
     * @return      Builder interface for setting the age field
     */
    public StudentBuilderAge name(String name) throws InvalidValueException
    {
        if(name != null) NameImpl.edma_validate(name);
        if(name == null) throw new NullPointerException();
        edma_value[0] = NameImpl.edma_create(name);
        return this;
    }

    /**
     * sets the age field.
     * @param age  The value to assign to the age field
     * @return     The created Student value
     */
    public Student age(Age age)
    {
        if(age == null) throw new NullPointerException("The field age in Student may not be null");
        edma_value[1] = ((IValueInstance) age).edma_getValue();
        return new StudentImpl(StudentImpl.edma_create(edma_value));
    }

    /**
     * sets the age field.
     * @param age  The value to assign to the age field
     * @return     The created Student value
     */
    public Student age(Integer age) throws InvalidValueException
    {
        if(age != null) AgeImpl.edma_validate(age);
        if(age == null) throw new NullPointerException();
        edma_value[1] = AgeImpl.edma_create(age);
        return new StudentImpl(StudentImpl.edma_create(edma_value));
    }
}
