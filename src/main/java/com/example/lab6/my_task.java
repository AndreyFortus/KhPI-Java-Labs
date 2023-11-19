package com.example.lab6;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class my_task extends Application {
    private final Color originalBackgroundColor = Color.DARKBLUE;

    public static void main(String[] args) {
        launch(args);
    }

    private void reset(Canvas canvas, Canvas lightCanvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        GraphicsContext lightGC = lightCanvas.getGraphicsContext2D();

        gc.setFill(originalBackgroundColor);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        drawRoad(gc);
        drawRoof(gc);
        drawWindows(gc);
        drawBody(gc);
        drawWheels(gc);

        lightGC.clearRect(0, 0, lightCanvas.getWidth(), lightCanvas.getHeight());
        drawHeadlight(lightGC);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Lab 6 Andrii Fortus");
        Group root = new Group();

// білий фон позаду малюнку
        Rectangle rect = new Rectangle(1800, 800, Color.WHITE);
        root.getChildren().add(rect);

// основний малюнок
        final Canvas canvas = new Canvas(1800, 800);
        GraphicsContext gc = canvas.getGraphicsContext2D();

// світло від фар
        final Canvas lightCanvas = new Canvas(1800, 800);
        GraphicsContext lgc = lightCanvas.getGraphicsContext2D();

// початкове відмалювання
        reset(canvas, lightCanvas);

        root.getChildren().addAll(canvas, lightCanvas);

// стирання малюнка
        lightCanvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, e -> {
            gc.clearRect(e.getX() - 1, e.getY() - 1, 10, 10);
            lgc.clearRect(e.getX() - 1, e.getY() - 1, 10, 10);
        });
// повернення малюнка потрійним кліком
        lightCanvas.addEventHandler(MouseEvent.MOUSE_CLICKED, t -> {
            if (t.getClickCount() == 3) {
                reset(canvas, lightCanvas);
            }
        });

        primaryStage.setScene(new Scene(root, Color.rgb(49, 14, 162)));
        primaryStage.show();
    }

    private void drawRoad(GraphicsContext gc) {
        gc.setFill(Color.rgb(102, 46, 11));
        gc.fillRect(0, 600, 1800, 200);
    }

    private void drawWindows(GraphicsContext gc) {
        LinearGradient gradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.AQUA),
                new Stop(1, Color.BLUE));
        gc.setFill(gradient);
        double[] xPoints = {60, 110, 400, 480};
        double[] yPoints = {430, 300, 300, 400};
        gc.fillPolygon(xPoints, yPoints, 4);

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(30);
        gc.strokeLine(390, 300, 390, 400);
        gc.stroke();

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(100);
        gc.strokeLine(180, 330, 180, 400);
        gc.stroke();
    }

    private void drawRoof(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillPolygon(new double[]{30, 80, 400, 480},
                new double[]{430, 280, 280, 400}, 4);
    }

    private void drawBody(GraphicsContext gc) {
        gc.setFill(Color.RED);
        gc.fillRoundRect(30, 400, 600, 130, 30, 30);
    }

    private void drawWheels(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillOval(50, 450, 150, 150);
        gc.fillOval(430, 450, 150, 150);

        gc.setFill(Color.ORANGE);
        gc.fillRoundRect(590, 420, 40, 60, 5, 5);
    }

    private void drawHeadlight(GraphicsContext gc) {
        gc.setFill(Color.YELLOW);
        gc.fillPolygon(new double[]{630, 1090, 1090, 630},
                new double[]{420, 320, 580, 480}, 4);
        gc.fillOval(960, 320, 260, 260);

        DropShadow dropShadow1 = new DropShadow(400, 0, -50, Color.YELLOW);
        gc.applyEffect(dropShadow1);
        DropShadow dropShadow2 = new DropShadow(200, 0, 50, Color.YELLOW);
        gc.applyEffect(dropShadow2);
    }
}
