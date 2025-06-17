package com.recipez.views;

import com.recipez.util.GlobalValues;

import javafx.collections.ListChangeListener;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;

public class AddIngredientView extends HBox{
    private ChoiceBox<String> cboxQuantity, cboxVolume, cboxUnitsOfVolume, cboxWeight, cboxUnitsOfWeight;
    private TextField tfIngredientNameInput;
    // private Button btnRemoveIngredient;

    public AddIngredientView() {
        this.setStyle(GlobalValues.COLOR_TEST_FORMATTING_THREE);
        // this.setMinWidth();
        // HBox.setHgrow(this, Priority.ALWAYS);
        // this.setAlignment(Pos.CENTER);
        createAddIngredientView();
        
       
    }

    public void createAddIngredientView(){
        this.cboxQuantity = new ChoiceBox<>();
        this.cboxVolume = new ChoiceBox<>();
        this.cboxUnitsOfVolume = new ChoiceBox<>();
        this.cboxWeight = new ChoiceBox<>();
        this.cboxUnitsOfWeight = new ChoiceBox<>();
        this.tfIngredientNameInput = new TextField("");
        this.cboxVolume.getItems().setAll(GlobalValues.VOLUMEVALUES);
        this.cboxUnitsOfVolume.getItems().setAll(GlobalValues.UNITSOFVOLUMEVALUES);
        
        this.getChildren().addAll(tfIngredientNameInput, cboxVolume, cboxUnitsOfVolume);

        formatAddIngredientView();
    }

    public void formatAddIngredientView(){
        this.tfIngredientNameInput.setFont(GlobalValues.SMALL_FONT);
        this.cboxVolume.setStyle(GlobalValues.SMALL_FONT_SIZE_STRING+GlobalValues.SMALL_FONT_FAMILY_STRING);    
        this.cboxUnitsOfVolume.setStyle(GlobalValues.SMALL_FONT_SIZE_STRING+GlobalValues.SMALL_FONT_FAMILY_STRING);    
        this.cboxUnitsOfVolume.setMinWidth(80);
        this.cboxUnitsOfVolume.setMaxWidth(80);
        this.cboxUnitsOfVolume.setPrefWidth(80);
    }

    public String getIngredientName(){
        return this.tfIngredientNameInput.getText();
    }  

    public String getIngredientVolume(){
        return this.cboxVolume.getValue();
    }

    public String getIngredientUnitsOfVolume(){
        return this.cboxUnitsOfVolume.getValue();
    }
  
    public void resetIngredientName(){
        this.tfIngredientNameInput.setText(" ");
    }

    public void resetIngredientVolume(){
        this.cboxVolume.setValue(" ");
    }

    public void resetIngredientUnitsOfVolume(){
        this.cboxUnitsOfVolume.setValue(" ");
    }

    public void resetAddIngredientView(){
        this.resetIngredientName();
        this.resetIngredientVolume();
        this.resetIngredientUnitsOfVolume();
    }


}
