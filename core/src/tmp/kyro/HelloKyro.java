package tmp.kyro;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.objenesis.instantiator.ObjectInstantiator;
import org.objenesis.strategy.InstantiatorStrategy;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class HelloKyro {
    static public void main (String[] args) throws Exception {
        Kryo kryo = new Kryo();
        kryo.register(SomeClass.class);
        kryo.setInstantiatorStrategy(new InstantiatorStrategy() {
            @Override
            public <T> ObjectInstantiator<T> newInstantiatorOf(Class<T> type) {
                return null;
            }
        });
        SomeClass object = new SomeClass();
        object.value = "Hello Kryo!bbbbbbbbbbbbbb";

        Output output = new Output(new FileOutputStream("file.bin"));
        kryo.writeObject(output, object);
        output.close();

        Input input = new Input(new FileInputStream("file.bin"));
        SomeClass object2 = kryo.readObject(input, SomeClass.class);
        System.out.println(object2.value);
        input.close();
    }
    static public class SomeClass {
        String value;
    }
}