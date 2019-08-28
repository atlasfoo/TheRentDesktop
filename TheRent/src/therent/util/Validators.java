package therent.util;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class Validators {

    //Metodo para Valideitor
    public static void ValidarCampos(JFXTextField campo, String tipo)
    {

        switch(tipo)
        {
            case "Letra":
                /*-------------------------------*/
                RequiredFieldValidator validar = new RequiredFieldValidator();
                campo.getValidators().add(validar);
                validar.setMessage("Campo vacío.");

                campo.focusedProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        if (!newValue)
                        {
                            campo.validate();
                        }
                    }
                });
                /*-------------------------------*/
                break;
            case "Numero":
                NumberValidator val = new NumberValidator();
                campo.getValidators().add(val);
                val.setMessage("Solo se permiten números.");

                campo.focusedProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        if (!newValue)
                        {
                            campo.validate();
                        }
                    }
                });
                break;
        }

    }
}
