package com.eis.cloud;

import com.eis.cloud.model.Department;
import com.eis.cloud.model.Employee;
import org.beanio.BeanWriter;
import org.beanio.StreamFactory;
import org.junit.Test;
import org.supercsv.cellprocessor.FmtDate;
import org.supercsv.cellprocessor.FmtNumber;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void testApp() {

        StreamFactory factory = StreamFactory.newInstance();
        // load the mapping file
        factory.loadResource("mapping.xml");


        Employee employee = new Employee();
        employee.setFirstName("Jennifer,,Jen");
        employee.setLastName("Jones");
        employee.setTitle("Marketing");
        employee.setSalary(60000);
        employee.setHireDate(new Date());

        List<Department> departmentList = new ArrayList<Department>();

        Department d1 = new Department();
        d1.setName("dept1");
        Department d2 = new Department();
        d2.setName("dept2");

        employee.getDepartments().add(d1);
        employee.getDepartments().add(d2);

        // use a StreamFactory to create a BeanWriter
        BeanWriter out = factory.createWriter("employeeFile", new File("employee.csv"));

        // write an Employee object directly to the BeanWriter
        out.write(employee);
        out.flush();
        out.close();

    }


    @Test
    public void testSuperCSV() throws IOException {

        Employee employee = new Employee();
        employee.setFirstName("Jennifer,,Jen");
        employee.setLastName("Jones");
        employee.setTitle("Marketing");
        employee.setSalary(60000);
        employee.setHireDate(new Date());

        List<Department> departmentList = new ArrayList<Department>();

        Department d1 = new Department();
        d1.setName("dept1");
        Department d2 = new Department();
        d2.setName("dept2");

        employee.getDepartments().add(d1);
        employee.getDepartments().add(d2);

        final String[] header = new String[]{"firstName", "lastName", "title", "salary", "hireDate", "departments"};

        final CellProcessor[] processors = new CellProcessor[]{
                new NotNull(),
                new Optional(),
                new Optional(),
                new FmtNumber("#####.00"),
                new FmtDate("MM/dd/yyyy"),
                new NotNull(),
        };

        ICsvBeanWriter beanWriter=null;

        try {
            beanWriter = new CsvBeanWriter(new FileWriter("target/writeWithCSVBeanWriter.csv"),
                    CsvPreference.STANDARD_PREFERENCE);

            beanWriter.writeHeader(header);

            beanWriter.write(employee, header, processors);

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally
        {
            if (beanWriter != null)
            {
                beanWriter.close();
            }
        }


    }
}


