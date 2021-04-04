package proj.generated.valuedomains.impl;

import org.abstractica.edma.valuedomains.IValueInstance;
import proj.generated.valuedomains.Active;
import proj.generated.valuedomains.As3;
import proj.generated.valuedomains.As3.As3BuilderActive;
import proj.generated.valuedomains.As3.As3BuilderCourses;
import proj.generated.valuedomains.As3.As3BuilderStudent;
import proj.generated.valuedomains.Courses;
import proj.generated.valuedomains.Student;
import proj.generated.valuedomains.impl.ActiveImpl;

/**
 * 
 */
public class As3BuilderImpl implements As3BuilderActive, As3BuilderCourses, As3BuilderStudent
{
    private Object[] edma_value;



    /**
     * Constructor
     */
    public As3BuilderImpl()
    {
        edma_value = new Object[3];
    }

    /**
     * sets the student field.
     * @param student  The value to assign to the student field
     * @return         Builder interface for setting the courses field
     */
    public As3BuilderCourses student(Student student)
    {
        if(student == null) throw new NullPointerException("The field student in As3 may not be null");
        edma_value[0] = ((IValueInstance) student).edma_getValue();
        return this;
    }

    /**
     * sets the courses field.
     * @param courses  The value to assign to the courses field
     * @return         Builder interface for setting the active field
     */
    public As3BuilderActive courses(Courses courses)
    {
        if(courses == null) throw new NullPointerException("The field courses in As3 may not be null");
        edma_value[1] = ((IValueInstance) courses).edma_getValue();
        return this;
    }

    /**
     * sets the active field.
     * @param active  The value to assign to the active field
     * @return        The created As3 value
     */
    public As3 active(Active active)
    {
        if(active == null) throw new NullPointerException("The field active in As3 may not be null");
        edma_value[2] = ((IValueInstance) active).edma_getValue();
        return new As3Impl(As3Impl.edma_create(edma_value));
    }

    /**
     * sets the active field.
     * @param active  The value to assign to the active field
     * @return        The created As3 value
     */
    public As3 active(Boolean active)
    {
        if(active == null) throw new NullPointerException();
        edma_value[2] = ActiveImpl.edma_create(active);
        return new As3Impl(As3Impl.edma_create(edma_value));
    }
}
