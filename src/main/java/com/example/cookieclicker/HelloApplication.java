package com.example.cookieclicker;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
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







public class HelloApplication extends Application {

    Image img = new Image("https://upload.wikimedia.org/wikipedia/commons/7/7f/Rotating_earth_animated_transparent.gif?20201022124448");
    Image img1 = new Image("https://www.nasa.gov/centers/goddard/images/content/387520main_92_80_1-334x312.gif");
    Image img2 = new Image("https://media3.giphy.com/media/v1.Y2lkPTc5MGI3NjExNWM3MjRmMWY4YTg5ZWFkYzgxOGU0N2QwNWVjMDlhNzRmMTViN2I3NSZjdD1n/iwJMmqOiqzss0/giphy.gif");
    Image img3 = new Image("https://pbs.twimg.com/profile_images/1623040457218396168/9LR7Jg9u_400x400.jpg");
    Image img4 = new Image("https://bbts1.azureedge.net/images/p/full/2022/12/9377e258-ce2b-4f17-bc8e-b18fa6b85a4f.jpg");
    Image red = new Image("https://www.iconsdb.com/icons/preview/soylent-red/dvd-xxl.png");
    Image white = new Image("https://www.iconsdb.com/icons/preview/white/dvd-xxl.png");
    Image yellow = new Image("https://www.iconsdb.com/icons/preview/color/FFD500/dvd-xxl.png");
    Image blue = new Image("https://www.iconsdb.com/icons/preview/caribbean-blue/dvd-xxl.png");
    Image orange = new Image("https://www.iconsdb.com/icons/preview/orange/dvd-xxl.png");
    Image green = new Image("https://www.iconsdb.com/icons/preview/guacamole-green/dvd-xxl.png");
    Image purple = new Image("https://www.iconsdb.com/icons/preview/color/9021FF/dvd-xxl.png");
    Image green1 = new Image("https://www.iconsdb.com/icons/preview/color/40FF93/dvd-xxl.png");
    Image pink = new Image("https://www.iconsdb.com/icons/preview/barbie-pink/dvd-xxl.png");
    Image bluegreen = new Image("https://www.iconsdb.com/icons/preview/color/00FFAA/dvd-xxl.png");
    Image darkblue = new Image("https://www.iconsdb.com/icons/preview/color/0B0BD6/dvd-xxl.png");
    Image[] dvd = new Image[]{red, white, yellow, blue, orange, green, purple, green1, pink, bluegreen, darkblue};
    public static Circle circle;
    public static Pane canvas;
    private double speed = 15;
    private float size = 127;
    private int counter = 0;
    private Label label = new Label("");
    private BackgroundMusic music = new BackgroundMusic();


    @Override
    public void start(Stage primaryStage) throws Exception {
        music.start(primaryStage);
        canvas = new Pane();
        Scene scene = new Scene(canvas, 1000, 800);


        label.setFont(Font.font("Roboto", FontWeight.EXTRA_LIGHT, 400));
        label.setTextFill(Color.rgb(242, 90, 90, .4));
        label.setMouseTransparent(true); //This line makes it so the label does not interfere with the circle being clicked

        primaryStage.setTitle("Game");
        primaryStage.setScene(scene);
        primaryStage.show();

        circle = new Circle(size, Color.TOMATO);
        circle.relocate(0, 0);
        circle.setFill(new ImagePattern(img));

        //canvas.getChildren().addAll(bg);
        canvas.getChildren().addAll(circle);

        EventHandler<MouseEvent> circleMeHandler = e -> handleEventforCircle(e); //handleEventforCircle is called when circle is clicked
        circle.addEventHandler(MouseEvent.MOUSE_CLICKED, circleMeHandler);

        EventHandler<MouseEvent> mouseMeHandler = e -> handleMouse(e, scene); //handleMouse which changes cursor shape is called when mouse goes over circle
        circle.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseMeHandler);

        EventHandler<MouseEvent> mouseHandler = e -> handleMouse1(e, scene); //handleMouse1 which changes cursor back is called when mouse leaves circle
        circle.addEventHandler(MouseEvent.MOUSE_EXITED, mouseHandler);

        final Timeline loop = new Timeline();
        KeyFrame kf = new KeyFrame(Duration.millis(speed), new EventHandler<ActionEvent>() {
            double deltaX = 3;
            double deltaY = 3;



            @Override
            public void handle(final ActionEvent t) {


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

                if (counter == 20){
                    circle.setFill(new ImagePattern(img2));
                    canvas.setStyle("-fx-background-color: #99ffe6");
                    loop.setRate(1.2);
                }

                if (counter == 50){
                    circle.setFill(new ImagePattern(img3));
                    canvas.setStyle("-fx-background-color: #9999ff");
                    loop.setRate(1.3);
                }
                if (counter == 100){
                    circle.setRadius(100);
                    circle.setFill(new ImagePattern(img4));
                    canvas.setStyle("-fx-background-color: #42f58d");
                    loop.setRate(1.4);
                }
                if(counter==200){
                    circle.setRadius(90);
                    circle.setFill(new ImagePattern(img1));
                    loop.setRate(2);

                }
                if (counter >= 300){
                    canvas.setStyle("-fx-background-color: black");
                    circle.setRadius(70);
                    loop.setRate(2.2);
                    int rnd1 = new Random().nextInt(dvd.length);
                    if(atRightBorder || atLeftBorder || atBottomBorder || atTopBorder){
                        int rnd = new Random().nextInt(dvd.length);
                        circle.setFill(new ImagePattern(dvd[rnd]));

                    }
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
        canvas.setStyle("-fx-background-color: #00b0e0");


        loop.setCycleCount(Timeline.INDEFINITE);
        loop.play();
    }


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
        Label secondLabel = new Label("YOU LOSE!");
        secondLabel.setFont(Font.font("Roboto", FontWeight.EXTRA_LIGHT, 300));
        Pane p = new Pane();
        StackPane secondaryLayout = new StackPane();


        p.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #686af7, #9fa0a1)");
        secondaryLayout.getChildren().add(p);
        secondaryLayout.getChildren().add(secondLabel);

        Scene secondScene = new Scene(secondaryLayout);


        // New window (Stage)
        Stage newWindow = new Stage();
        //newWindow.setFullScreen(true);
        newWindow.setTitle("YOU LOSE");
        newWindow.setScene(secondScene);


        primaryStage.close();

        newWindow.show();
    }
}