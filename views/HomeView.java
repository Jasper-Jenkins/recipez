package views;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import util.GlobalValues;

public class HomeView extends Scene {
    private Pane root;      
    private Button btnRecipeCreate, btnRecipeBook, btnMealPlanner;
    private HBox hboxNavigation;
    private VBox vboxApplication, vboxHomeScreen;
    private Label welcome;
    private StackPane spScreen;

    //The views for our application 
    private RecipeView recipeView;
    // private RecipeBookView recipeBookView;
    // private MealPlannerView MealPlannerView;
    
    public HomeView() {
        //Constructor call super() to parent Scene
        //Change varbiable values in GlobalValues class to alter aspect ratio.
        super(new Pane(), GlobalValues.APP_WIDTH, GlobalValues.APP_HEIGHTH);                 
        createView();
    }

    private void createView(){
        //intializing 
        root = ((Pane)this.getRoot());
        this.recipeView = new RecipeView();
        // this.recipeBookView = new RecipeBookView();
        // this.mealPlannerView = new MealPlannerView();

        //Application Navigation buttons initialized
        this.btnRecipeCreate = new Button("New Recipe");
        this.btnRecipeBook = new Button("Recipe Book");
        this.btnMealPlanner = new Button("Meal Planner");

        //vboxHomeScreen is the element that will be swapped out for our views
        //Welcome message to the user when first starting app, will be populated in vBoxHomeScreen.
        this.vboxHomeScreen = new VBox();
        this.welcome = new Label("Welcome!");      
        
        //vBoxApplication is where the the whole of the application will be housed. 
        //This StackPane will be where our views will display
        this.vboxApplication = new VBox();
        this.spScreen = new StackPane();
        this.hboxNavigation = new HBox();
        
        //setting up the fluff, the pretty stuff
        this.welcome.setFont(GlobalValues.LARGE_FONT);

        //Vbox for welcome screen prettified
        this.vboxHomeScreen.setPrefHeight(GlobalValues.VIEW_HEIGHTH);
        this.vboxHomeScreen.setAlignment(Pos.CENTER);
        this.vboxHomeScreen.setStyle(GlobalValues.COLOR_TEST_FORMATTING_TWO);

        //navigation holder prettified
        this.hboxNavigation.setPrefWidth(GlobalValues.APP_WIDTH);
        this.hboxNavigation.setPrefHeight(GlobalValues.NAV_HEIGHTH);
        this.hboxNavigation.setAlignment(Pos.CENTER);

        //setting up action for click on navigation buttons
        this.btnRecipeCreate.setOnAction(this::navigation);
        this.btnRecipeBook.setOnAction(this::navigation);
        this.btnMealPlanner.setOnAction(this::navigation);

        // Putting the UI elements together
        this.vboxHomeScreen.getChildren().addAll(this.welcome);
        this.spScreen.getChildren().addAll(vboxHomeScreen);
        this.hboxNavigation.getChildren().addAll(btnRecipeCreate, btnRecipeBook, btnMealPlanner);
        this.vboxApplication.getChildren().addAll(spScreen, hboxNavigation);
        this.root.getChildren().addAll(vboxApplication);
    }

    //Fixed navigation issue, buttons will disable/enable as appropriate. May need a efficiency rework
    private void navigation(ActionEvent event){
        String buttonText = ((Button)event.getSource()).getText();
        if (buttonText == "New Recipe") {
            for(Node button : this.hboxNavigation.getChildren()){
                ((Button)button).setDisable(false);
            }
            this.spScreen.getChildren().clear();
            this.spScreen.getChildren().add(this.recipeView);
            ((Button)event.getSource()).setDisable(true); 
        }
        if (buttonText == "Recipe Book") {
            for(Node button : this.hboxNavigation.getChildren()){
                ((Button)button).setDisable(false);
            }
            // this.spScreen.getChildren().clear();
            // this.spScreen.getChildren().add(this.recipeBookView);
            ((Button)event.getSource()).setDisable(true); 
        }
        if (buttonText == "Meal Planner") {
            for(Node button : this.hboxNavigation.getChildren()){
                ((Button)button).setDisable(false);
            }
            // this.spScreen.getChildren().clear();
            // this.spScreen.getChildren().add(this.mealPlannerView);
            ((Button)event.getSource()).setDisable(true); 
        }
    }
}
