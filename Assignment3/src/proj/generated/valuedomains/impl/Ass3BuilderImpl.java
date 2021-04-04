package proj.generated.valuedomains.impl;

import org.abstractica.edma.valuedomains.IValueInstance;
import proj.generated.valuedomains.Active;
import proj.generated.valuedomains.Ass3;
import proj.generated.valuedomains.Ass3.Ass3BuilderActive;
import proj.generated.valuedomains.Ass3.Ass3BuilderCourses;
import proj.generated.valuedomains.Ass3.Ass3BuilderStudent;
import proj.generated.valuedomains.Courses;
import proj.generated.valuedomains.Student;
import proj.generated.valuedomains.impl.ActiveImpl;

/**
 * 
 */
public class Ass3BuilderImpl implements Ass3BuilderActive, Ass3BuilderCourses, Ass3BuilderStudent
{
    private Object[] edma_value;



    /**
     * Constructor
     */
    public Ass3BuilderImpl()
    {
        edma_value = new Object[3];
    }

    /**
     * sets the student field.
     * @param student  The value to assign to the student field
     * @return         Builder interface for setting the courses field
     */
    public Ass3BuilderCourses student(Student student)
    {
        if(student == null) throw new NullPointerException("The field student in Ass3 may not be null");
        edma_value[0] = ((IValueInstance) student).edma_getValue();
        return this;
    }

    /**
     * sets the courses field.
     * @param courses  The value to assign to the courses field
     * @return         Builder interface for setting the active field
     */
    public Ass3BuilderActive courses(Courses courses)
    {
        if(courses == null) throw new NullPointerException("The field courses in Ass3 may not be null");
        edma_value[1] = ((IValueInstance) courses).edma_getValue();
        return this;
    }

    /**
     * sets the active field.
     * @param active  The value to assign to the active field
     * @return        The created Ass3 value
     */
    public Ass3 active(Active active)
    {
        if(active == null) throw new NullPointerException("The field active in Ass3 may not be null");
        edma_value[2] = ((IValueInstance) active).edma_getValue();
        return new Ass3Impl(Ass3Impl.edma_create(edma_value));
    }

    /**
     * sets the active field.
     * @param active  The value to assign to the active field
     * @return        The created Ass3 value
     */
    public Ass3 active(Boolean active)
    {
        if(active == null) throw new NullPointerException();
        edma_value[2] = ActiveImpl.edma_create(active);
        return new Ass3Impl(Ass3Impl.edma_create(edma_value));
    }
}
