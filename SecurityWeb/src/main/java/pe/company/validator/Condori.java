package pe.company.validator;

import java.lang.annotation.*;
import javax.validation.*;
import pe.company.validator.Condori.*;


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=TextValidator.class)
public @interface Condori 
{
	String message() default "{application.error.Text}"; //si no tiene un mensaje de error, por defecto tendrá uno
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    
    TextType value();
    
    public static class TextValidator implements ConstraintValidator<Condori,String>
    {
    	private Condori text;        

        public void initialize(Condori text) 
        {
        	this.text=text;
        }
        
		@Override
		public boolean isValid(String str, ConstraintValidatorContext context)
		{
			boolean valid=true;
			
			if(str.isEmpty())
                return false;
			
			else if(text.value()==TextType.LETTERS)
            {
                if(!str.trim().matches("[a-zA-ZáÁéÉíÍóÓúÚüÜñÑ ]*"))
                    valid=false;
            }
			
			//Validación de un usuario
            //solo texto de tipo username (alfanumerico entre 4 y 16 caracteres)
            else if(text.value()==TextType.USERNAME)
            {
                if(!((!str.contains(" "))&&((str.length()>=4)&&(str.length()<=16))))
                    valid=false;
                
                /*Nota: 
                  - No se permite espacio en blanco, rango de caracteres (4-16).
                */
            }
			
			//Validación de un password
            //solo texto de tipo password (alfanumerico entre 4 y 60 caracteres)
            else if(text.value()==TextType.PASSWORD)
            {
                if(!((!str.contains(" "))&&((str.length()>=4)&&(str.length()<=60))))
                    valid=false;
                
                /*Notas: 
                  - No se permite espacio en blanco, rango de caracteres (4-60).
                  - Al password se le asigna un rango máximo de 60 caracteres, esto es a que al ser encriptado generará 
                    dicha cantidad.
                */
            }
			
			return valid;

			
		}
    	
    }

	
	//se define los tipos de texto
    public enum TextType {
        LETTERS,USERNAME,PASSWORD
    }


}
