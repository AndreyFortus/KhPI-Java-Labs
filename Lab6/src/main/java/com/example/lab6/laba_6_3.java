package com.example.lab6;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
public class laba_6_3 extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    /** * Скидає полотно в початковий стан */
    private void reset(Canvas canvas, Color color) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(color);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("PaintApp");
        Group root = new Group();
// Задній фон
        Rectangle rect = new Rectangle(500, 500, Color.WHITE);
        root.getChildren().add(rect);
// Передній фон
        final Canvas canvas = new Canvas(500, 500);
// canvas.setTranslateX(0);
// canvas.setTranslateY(0);
        reset(canvas, Color.DARKBLUE);
        final GraphicsContext gc = canvas.getGraphicsContext2D();
// Видалення переднього фону при натисканні ЛКМ та переміщенні по полотну
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, new
                EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        gc.clearRect(e.getX() - 1, e.getY() - 1, 2, 2);
                    }
                });
// Повернення переднього фону при подвійному кліці
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, new
                EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        if (t.getClickCount() >1) {
                            reset(canvas, Color.DARKBLUE);
                        }
                    }
                });
// Додавання полотна до сцени та відображення сцени
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }
}
