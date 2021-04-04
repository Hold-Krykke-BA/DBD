package proj.generated.valuedomains.impl;

import org.abstractica.edma.valuedomains.IValueInstance;
import org.abstractica.edma.valuedomains.exceptions.InvalidValueException;
import proj.generated.valuedomains.Course;
import proj.generated.valuedomains.Course.CourseBuilderEcts;
import proj.generated.valuedomains.Course.CourseBuilderId;
import proj.generated.valuedomains.Course.CourseBuilderName;
import proj.generated.valuedomains.Ects;
import proj.generated.valuedomains.Id;
import proj.generated.valuedomains.Name;
import proj.generated.valuedomains.impl.EctsImpl;
import proj.generated.valuedomains.impl.IdImpl;
import proj.generated.valuedomains.impl.NameImpl;

/**
 * 
 */
public class CourseBuilderImpl implements CourseBuilderEcts, CourseBuilderId, CourseBuilderName
{
    private Object[] edma_value;



    /**
     * Constructor
     */
    public CourseBuilderImpl()
    {
        edma_value = new Object[3];
    }

    /**
     * sets the id field.
     * @param id  The value to assign to the id field
     * @return    Builder interface for setting the name field
     */
    public CourseBuilderName id(Id id)
    {
        if(id == null) throw new NullPointerException("The field id in Course may not be null");
        edma_value[0] = ((IValueInstance) id).edma_getValue();
        return this;
    }

    /**
     * sets the id field.
     * @param id  The value to assign to the id field
     * @return    Builder interface for setting the name field
     */
    public CourseBuilderName id(Integer id) throws InvalidValueException
    {
        if(id != null) IdImpl.edma_validate(id);
        if(id == null) throw new NullPointerException();
        edma_value[0] = IdImpl.edma_create(id);
        return this;
    }

    /**
     * sets the name field.
     * @param name  The value to assign to the name field
     * @return      Builder interface for setting the ects field
     */
    public CourseBuilderEcts name(Name name)
    {
        if(name == null) throw new NullPointerException("The field name in Course may not be null");
        edma_value[1] = ((IValueInstance) name).edma_getValue();
        return this;
    }

    /**
     * sets the name field.
     * @param name  The value to assign to the name field
     * @return      Builder interface for setting the ects field
     */
    public CourseBuilderEcts name(String name) throws InvalidValueException
    {
        if(name != null) NameImpl.edma_validate(name);
        if(name == null) throw new NullPointerException();
        edma_value[1] = NameImpl.edma_create(name);
        return this;
    }

    /**
     * sets the ects field.
     * @param ects  The value to assign to the ects field
     * @return      The created Course value
     */
    public Course ects(Ects ects)
    {
        if(ects == null) throw new NullPointerException("The field ects in Course may not be null");
        edma_value[2] = ((IValueInstance) ects).edma_getValue();
        return new CourseImpl(CourseImpl.edma_create(edma_value));
    }

    /**
     * sets the ects field.
     * @param ects  The value to assign to the ects field
     * @return      The created Course value
     */
    public Course ects(Integer ects) throws InvalidValueException
    {
        if(ects != null) EctsImpl.edma_validate(ects);
        if(ects == null) throw new NullPointerException();
        edma_value[2] = EctsImpl.edma_create(ects);
        return new CourseImpl(CourseImpl.edma_create(edma_value));
    }
}
