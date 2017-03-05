package me.Ikillnukes.uchijatools.application;

import java.io.IOException;

import insidefx.undecorator.UndecoratorScene;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainApp extends Application 
{
	
	private Stage primaryStage;
	private Region rootWindow;

	@Override
	public void start(final Stage primaryStage) 
	{
        this.primaryStage = primaryStage;
        this.primaryStage.setResizable(false);
        this.primaryStage.setTitle("UCHIJA's Tools");
        

        initRootWindow();
	}
	
	/**
     * Initializes the root window.
     */
    public void initRootWindow() {
        try 
        {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/MainLayout.fxml"));
            loader.setController(this);
            rootWindow = (Region)loader.load();
            
            final UndecoratorScene undecoratorScene = new UndecoratorScene(primaryStage, rootWindow);
            undecoratorScene.getStylesheets().add("me/Ikillnukes/uchijatools/resources/app.css");
            undecoratorScene.setFadeInTransition();
            
            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent we) {
                    we.consume();   // Do not hide yet
                    undecoratorScene.setFadeOutTransition();
                }
            });
            
            primaryStage.setScene(undecoratorScene);
            primaryStage.show();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

	public static void main(String[] args) 
	{
		launch(args);
	}
}
