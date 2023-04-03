package com.example.cookieclicker;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.css.converter.FontConverter;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.Random;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;






public class HelloApplication extends Application {
    //Image img = new Image("https://corporate.target.com/_media/TargetCorp/Press/B-roll%20and%20Press%20Materials/Logos/Target_Bullseye-Logo_Red_transparent.png?preset=640w");
    Image img = new Image("https://upload.wikimedia.org/wikipedia/commons/2/2c/Rotating_earth_%28large%29.gif");
    //Image img = new Image("https://witdirectoryphoto.z13.web.core.windows.net/mark.thompson.jpg");
    //Image img = new Image("https://i.redd.it/yvwiwgcgdwr91.gif");
    Image img1 = new Image("https://witdirectoryphoto.z13.web.core.windows.net/mark.thompson.jpg");
    Image img2 = new Image("https://media.licdn.com/dms/image/C4D03AQGKjGkn-IQ5kw/profile-displayphoto-shrink_800_800/0/1528981427807?e=2147483647&v=beta&t=2-HMbPxa6EioyDQS_5K19PXrN1yWvWeYW5vk9CnFue4");
    Image img3 = new Image("https://witdirectoryphoto.z13.web.core.windows.net/rosenbergd.jpg");

    public static Circle circle;
    //public static Button bg; //make button so background is clickable
    public static Pane canvas;
    private double speed = 2;
    private float size = 100;

    private int counter = 0;
    private Label label = new Label("");


    @Override
    public void start(Stage primaryStage) {
        canvas = new Pane();
        Scene scene = new Scene(canvas, 1000, 800);


        label.setFont(Font.font("Roboto", FontWeight.EXTRA_LIGHT, 400));
        label.setTextFill(Color.rgb(242, 90, 90, .4));
        label.setMouseTransparent(true); //This line makes it so the label does not interfere with the circle being clicked

        primaryStage.setTitle("Game");
        primaryStage.setScene(scene);
        primaryStage.show();

//        bg = new Button("Click to lose");
//        bg.relocate(0,0);
//        bg.resize(1000,800);

        circle = new Circle(size, Color.TOMATO);
        circle.relocate(0, 0);
        circle.setFill(new ImagePattern(img));

        //canvas.getChildren().addAll(bg);
        canvas.getChildren().addAll(circle);


//        circle.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                Point2D point2D = new Point2D(event.getX(), event.getY());
//                if (!(circle.contains(point2D))) {
//                    EventHandler<MouseEvent> deathHandler = e -> handleDeath(e, scene, primaryStage); //deathHandler is called which brings up end screen when circle is missed
//                    circle.addEventHandler(MouseEvent.MOUSE_CLICKED, deathHandler);
//
//                }
//            }
//        });

        EventHandler<MouseEvent> circleMeHandler = e -> handleEventforCircle(e); //handleEventforCircle is called when circle is clicked
        circle.addEventHandler(MouseEvent.MOUSE_CLICKED, circleMeHandler);

        EventHandler<MouseEvent> mouseMeHandler = e -> handleMouse(e, scene); //handleMouse which changes cursor shape is called when mouse goes over circle
        circle.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseMeHandler);

        EventHandler<MouseEvent> mouseHandler = e -> handleMouse1(e, scene); //handleMouse1 which changes cursor back is called when mouse leaves circle
        circle.addEventHandler(MouseEvent.MOUSE_EXITED, mouseHandler);

        //EventHandler<MouseEvent> deathHandler = e -> handleDeath(e, scene, primaryStage); //deathHandler is called which brings up end screen when circle is missed
        //bg.addEventHandler(MouseEvent.MOUSE_CLICKED, deathHandler);
//        MouseEvent m = new MouseEvent();
//        Point2D point2D = new Point2D( .getX();, .getY());
//        if (circle.contains(point2D)) {
//            System.out.println("circle clicked");
//        }

//
//        double X = circle.getCenterX();
//        double Y = circle.getCenterY();
//        double rad = circle.getRadius();
//        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, deathHandler);
        final Timeline loop = new Timeline();
        KeyFrame kf = new KeyFrame(Duration.millis(speed), new EventHandler<ActionEvent>() {
            double deltaX = 3;
            double deltaY = 3;



            @Override
            public void handle(final ActionEvent t) {

                if (counter==5){

                    circle.setFill(new ImagePattern(img1));
                    //loop.getKeyFrames().addAll(kf,new KeyFrame(Duration.millis(3)));
                }

                if (counter == 20){
                    circle.setFill(new ImagePattern(img2));
                }

                if (counter == 100){
                    circle.setFill(new ImagePattern(img3));
                }
                circle.setLayoutX(circle.getLayoutX() + deltaX);
                circle.setLayoutY(circle.getLayoutY() + deltaY);

                final Bounds bounds = canvas.getBoundsInLocal();
                final boolean atRightBorder = circle.getLayoutX() >= (bounds.getMaxX() - circle.getRadius());
                final boolean atLeftBorder = circle.getLayoutX() <= (bounds.getMinX() + circle.getRadius());
                final boolean atBottomBorder = circle.getLayoutY() >= (bounds.getMaxY() - circle.getRadius());
                final boolean atTopBorder = circle.getLayoutY() <= (bounds.getMinY() + circle.getRadius());

                if (atRightBorder || atLeftBorder) {
                    deltaX *= -1;
                }
                if (atBottomBorder || atTopBorder) {
                    deltaY *= -1;
                }
            }
        });


        scene.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                Bounds circleBounds = circle.getBoundsInParent();
                if (!circleBounds.contains(event.getX(), event.getY())) {
                    // Open a new window that says "You Lose"
                    handleDeath(event, scene, primaryStage);
                }
            }
        });

        loop.getKeyFrames().addAll(kf, new KeyFrame(Duration.millis(.1)));


        label.relocate(200, 100);
        canvas.getChildren().add(label);


        loop.setCycleCount(Timeline.INDEFINITE);
        loop.play();


    }

//    public boolean isMouseClose(MouseEvent event, double X, double Y, double rad) {
//        double x = event.getX();
//        double y = event.getY();
//        if((X>=x+rad||X<=x-rad)&&(Y>=y+rad||Y<=y-rad)){
//            return true;
//        }
//        return false;
//    }

    public void counter() {
        counter++;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void handleEventforCircle(MouseEvent e) {
        counter();
        label.setText(Integer.toString(counter));
    }

    public void handleMouse(MouseEvent e, Scene scene) {
        scene.setCursor(Cursor.CROSSHAIR);
    }

    public void handleMouse1(MouseEvent e, Scene scene) {
        scene.setCursor(Cursor.DEFAULT);
    }


    public void handleDeath(MouseEvent e, Scene scene, Stage primaryStage) {
        Label secondLabel = new Label("YOU LOSE");
        secondLabel.setFont(Font.font("Roboto", FontWeight.EXTRA_LIGHT, 50));
        Pane p = new Pane();
        StackPane secondaryLayout = new StackPane();


        p.setStyle("-fx-background-color: red;");
        secondaryLayout.getChildren().add(p);
        secondaryLayout.getChildren().add(secondLabel);

        Scene secondScene = new Scene(secondaryLayout, 230, 100);

        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("YOU LOSE");
        newWindow.setScene(secondScene);


        // Set position of second window, related to primary window.
        newWindow.setX(200);
        newWindow.setY(100);
        primaryStage.close();
        newWindow.show();
    }
}