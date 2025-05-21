package com.recipez.views;

import java.io.File;

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
import com.recipez.models.ObserverModel;
import com.recipez.util.GlobalValues;
import com.recipez.util.Observer;

public class HomeView extends Scene {
    private Pane root;      
    private Button btnCreateRecipe, btnRecipeBook, btnMealPlanner;
    private HBox hboxNavigation;
    private VBox vboxApplication, vboxHomeScreen;
    private Label lblWelcome;
    private StackPane spViewDisplay;
    
    //The views for our application 
    private CreateRecipeView createRecipeView;
    private RecipeBookView recipeBookView;
    // private MealPlannerView MealPlannerView;

    //Observer Pattern solution (INCOMPLETE)
    private ObserverModel observerModel = new ObserverModel();
    private Observer[] observers = new Observer[2]; // will end up being 3: recipeBook, groceryList, and mealPlanner should be aware of updates


    public HomeView() {
        //Constructor call super() to parent Scene
        //Change varbiable values in GlobalValues class to alter aspect ratio.
        super(new Pane(), GlobalValues.APP_WIDTH, GlobalValues.APP_HEIGHT);                 
        
        // Creates a local directory for data persistence. Recipe,
        // RecipeBook, and MealPlan will be saved in the src\main\resources\data directory
        // new File(workingDir+"\\src\\main\\resources\\data").mkdir()
        // the double \\ lets you insert a \ into a String.   
        

        // workingDir is the directory where we are currently running the app from
        // which should be c:\some\path\to\your\code\recipez-core 
        String workingDir = System.getProperty("user.dir");
        System.out.println("Current working directory: " + workingDir);
        if(new File(workingDir+"\\src\\main\\resources\\data").mkdir()){
            System.out.println("Directory created:\n"+workingDir+"\\src\\main\\resources\\data");
        }else{
            System.out.println("Directory already exists:\n"+workingDir+"\\src\\main\\resources\\data");
        }
        createView();
    }

    private void createView(){
        //intializing 
        root = ((Pane)this.getRoot());
        this.createRecipeView = new CreateRecipeView(this.observerModel);
        this.recipeBookView = new RecipeBookView(this.observerModel);
        // this.mealPlannerView = new MealPlannerView();

        //observers set up
        this.observers[0] = this.createRecipeView;
        this.observers[1] = this.recipeBookView;
        
        //Application Navigation buttons initialized
        this.btnCreateRecipe = new Button("New Recipe");
        this.btnRecipeBook = new Button("Recipe Book");
        this.btnMealPlanner = new Button("Meal Planner");

        // vboxHomeScreen is the element that will be swapped out for our views
        // lblWelcome stores a message to the user when first starting app, populate in vBoxHomeScreen.
        this.vboxHomeScreen = new VBox();
        this.lblWelcome = new Label("Welcome!");      
        
        // vboxApplication is where the the whole of the application will be housed. Vbox was chosen to stack our views with the navigation below it. 
        // The spViewDisplay StackPane is where our views will display
        this.vboxApplication = new VBox();
        this.spViewDisplay = new StackPane();
        this.hboxNavigation = new HBox();

        //trying to dynamic resize
        this.vboxApplication.setStyle(GlobalValues.COLOR_PRIMARY);
        //Dynamic resize of top Parent Node element inside of root
        this.vboxApplication.prefWidthProperty().bind(this.widthProperty());
        //setting up the fluff, the pretty stuff
        this.lblWelcome.setFont(GlobalValues.LARGE_FONT);
        this.createRecipeView.prefWidthProperty().bind(this.widthProperty());
        this.createRecipeView.setStyle(GlobalValues.COLOR_TEST_FORMATTING_TWO);

        //Vbox for welcome screen prettified
        this.vboxHomeScreen.setPrefHeight(GlobalValues.VIEW_HEIGHT);
        this.vboxHomeScreen.setAlignment(Pos.CENTER);
        this.vboxHomeScreen.setStyle(GlobalValues.COLOR_TEST_FORMATTING_TWO);

        //navigation holder prettified
        this.hboxNavigation.setPrefWidth(GlobalValues.APP_WIDTH);
        this.hboxNavigation.setPrefHeight(GlobalValues.NAV_HEIGHT);
        this.hboxNavigation.setStyle(GlobalValues.COLOR_TEST_FORMATTING_ONE);
        this.hboxNavigation.setAlignment(Pos.CENTER);

        //setting up action for click on navigation buttons
        this.btnCreateRecipe.setOnAction(this::navigation);
        this.btnRecipeBook.setOnAction(this::navigation);
        this.btnMealPlanner.setOnAction(this::navigation);

        // Putting the UI elements together
        this.vboxHomeScreen.getChildren().addAll(this.lblWelcome);
        this.spViewDisplay.getChildren().addAll(vboxHomeScreen);
        this.hboxNavigation.getChildren().addAll(btnCreateRecipe, btnRecipeBook, btnMealPlanner);
        this.vboxApplication.getChildren().addAll(spViewDisplay, hboxNavigation);
        this.root.getChildren().addAll(vboxApplication);
    }

    //Fixed navigation issue, buttons will disable/enable as appropriate. May need a efficiency rework
    private void navigation(ActionEvent event){
        String buttonText = ((Button)event.getSource()).getText();
        if (buttonText == "New Recipe") {
            //setDisable(false) sets all the buttons to abled
            for(Node button : this.hboxNavigation.getChildren()){
                ((Button)button).setDisable(false);
            }
            for(Observer observer : this.observers){
                observer.update(observerModel.getCurrentUpdate());
            }
            this.spViewDisplay.getChildren().clear();
            this.spViewDisplay.getChildren().add(this.createRecipeView);
            ((Button)event.getSource()).setDisable(true); 
        }else if (buttonText == "Recipe Book") {
            for(Node button : this.hboxNavigation.getChildren()){
                ((Button)button).setDisable(false);
            }
            for(Observer observer : this.observers){
                observer.update(observerModel.getCurrentUpdate());
            }
            this.spViewDisplay.getChildren().clear();
            this.spViewDisplay.getChildren().add(this.recipeBookView);
            ((Button)event.getSource()).setDisable(true); 
        }else if (buttonText == "Meal Planner") {
            for(Node button : this.hboxNavigation.getChildren()){
                ((Button)button).setDisable(false);
            }
            for(Observer observer : this.observers){
                observer.update(observerModel.getCurrentUpdate());
            }
            // this.spViewDisplay.getChildren().clear();
            // this.spViewDisplay.getChildren().add(this.mealPlannerView);
            ((Button)event.getSource()).setDisable(true); 
        }
    }
}
// create insta cart ability to order ingredients on line, option for produce and meat to be picked up manually.
// Create two instruction types, prep instructions and cooking instructions
// suggestive input for ingredients. start typing and it suggest ingredients based on input, and auto fills whatever you select
