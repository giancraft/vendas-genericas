package DTO;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class DTOBase {

    public Object[] toArray() {
    	Object obj = this;
        List<Object> values = new ArrayList<>();
        Class<?> clazz = obj.getClass(); // Obtém a classe do objeto

        // Obtém os campos (atributos) da classe atual e suas superclasses
        while (clazz != null) {
            Field[] fields = clazz.getDeclaredFields(); // Obtém os campos da classe atual

            // Itera sobre os campos
            for (Field field : fields) {
                field.setAccessible(true); // Torna o campo acessível (mesmo se for privado)

                try {
                    // Adiciona o valor do campo à lista
                    values.add(field.get(obj));
                } catch (IllegalAccessException e) {
                    e.printStackTrace(); // Trate a exceção de acesso ilegal aqui
                }
            }

            clazz = clazz.getSuperclass(); // Move para a superclasse
        }

        // Converte a lista de valores em um array e o retorna
        return values.toArray();
    }
}
