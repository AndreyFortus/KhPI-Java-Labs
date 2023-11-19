package com.example.lab6;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
public class laba_6_2 extends Application {
    private Canvas canvas = new Canvas(200, 200);
    private GraphicsContext gc = canvas.getGraphicsContext2D();
    private Group root = new Group();
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Laba_6_1");
        moveCanvas(0,0);
        drawHeart();
        drawRadialGradient(Color.BLACK, Color.HOTPINK);
        drawLinearGradient(Color.AQUA, Color.DARKBLUE);
        drawDropShadow(Color.CHARTREUSE, Color.YELLOW, Color.ORANGE, Color.RED);
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root, 200, 200));
        primaryStage.show();
    }
    /** *
     Переміщуємо полотно на нове місце у сцені
     */
    private void moveCanvas(int x, int y) {
        canvas.setTranslateX(x);
        canvas.setTranslateY(y);
    }
    /** *
     Малюємо серце за допомогою кубічних кривих Без’є
     */
    private void drawHeart() {
        gc.beginPath();
        gc.moveTo(95,60);
        gc.bezierCurveTo(95,57,90,45,70,45);
        gc.bezierCurveTo(40,45,40,82.5,40,82.5);
        gc.bezierCurveTo(40,100,60,122,95,140);
        gc.bezierCurveTo(130,122,150,100,150,82.5);
        gc.bezierCurveTo(150,82.5,150,45,120,45);
        gc.bezierCurveTo(105,45,95,57,95,60);
        gc.closePath();
    }
    /** *
     Радіальний градієнт
     */
    private void drawRadialGradient(Color firstColor, Color lastColor) {
        gc.setFill(new RadialGradient(0, 0, 0.5, 0.5, 0.1, true,
                CycleMethod.REFLECT,
                new Stop(0.0, firstColor),
                new Stop(1.0, lastColor)));
        gc.fill();
    }
    /** *
     Лінійний градієнт
     */
    private void drawLinearGradient(Color firstColor, Color secondColor) {
        LinearGradient lg = new LinearGradient(0, 0, 1, 1, true,
                CycleMethod.REFLECT,
                new Stop(0.0, firstColor),
                new Stop(1.0, secondColor));
        gc.setStroke(lg);
        gc.setLineWidth(3);
        gc.stroke();
    }
    /**
     * Тінь з 4 кольорів
     */
    private void drawDropShadow(Color firstColor, Color secondColor, Color thirdColor, Color
            fourthColor) {
        gc.applyEffect(new DropShadow(20, 20, 0, firstColor));
        gc.applyEffect(new DropShadow(20, 0, 20, secondColor));
        gc.applyEffect(new DropShadow(20, -20, 0, thirdColor));
        gc.applyEffect(new DropShadow(20, 0, -20, fourthColor));
    }
}