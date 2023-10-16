package br.com.lucasrosasc.todolist.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class Utils {
    /*O static permite o não instanciamento da classe - pense em outras classes */
    public static void copyNonNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));

    }
    

    public static String[] getNullPropertyNames(Object source) {
        /*BeanWrapper = Classe do Java > Interface que fornece  meio de acesso às propriedades de um objeto no Java.
         * BeanWrapperImpl = implementação do BeanWrapper
        */
        final BeanWrapper src = new BeanWrapperImpl(source);
        /*Criação de array com todas as propriedades dentro do objeto (abaixo)*/
         PropertyDescriptor[] pds = src.getPropertyDescriptors();
         //Lista/Set vazio para inserir as informações nulas
         Set<String> emptyNames = new HashSet<>();

         for(PropertyDescriptor pd: pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if(srcValue == null) {
                emptyNames.add(pd.getName());
            }
         }

         String[] result = new String[emptyNames.size()];
         return emptyNames.toArray(result);
    }

}
