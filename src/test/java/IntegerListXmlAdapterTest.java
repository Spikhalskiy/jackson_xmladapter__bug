import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;
import com.google.common.base.Joiner;

import static java.util.Arrays.asList;

public class IntegerListXmlAdapterTest {
    @Test
    public void testBasic() throws JsonProcessingException {
        ObjectMapper mapper = (new ObjectMapper()).setAnnotationIntrospector(new JaxbAnnotationIntrospector());
        SomeIntListHolder listHolder = new SomeIntListHolder();
        listHolder.setListOne(asList(1, 2, 3));
        System.out.println(mapper.writeValueAsString(listHolder));
    }

    public static class IntegerListXmlAdapter extends XmlAdapter<Object, List<Integer>> {
        @Override
        public List<Integer> unmarshal(Object value) throws Exception {return null;}

        @Override
        public Object marshal(List<Integer> list) throws Exception {
            return Joiner.on(",").join(list);
        }
    }

    public static class IntegerListToStringXmlAdapter extends XmlAdapter<String, List<Integer>> {
        @Override
        public List<Integer> unmarshal(String value) throws Exception {return null;}

        @Override
        public String marshal(List<Integer> list) throws Exception {
            return Joiner.on(",").join(list);
        }
    }

    /**
     * Sample class, which holds list of integers.
     */
    @XmlRootElement
    @XmlAccessorType(XmlAccessType.NONE)
    public static class SomeIntListHolder {

        @XmlAttribute
        @XmlJavaTypeAdapter(IntegerListXmlAdapter.class)
        private List<Integer> listOne;

        public List<Integer> getListOne() {
            return listOne;
        }

        public void setListOne(List<Integer> listOne) {
            this.listOne = listOne;
        }
    }
}
