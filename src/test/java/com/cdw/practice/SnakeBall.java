package com.cdw.practice;

import java.time.Duration;
import java.time.Instant;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SnakeBall {
	public static void main(String[] args) throws InterruptedException {
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://letcode.in/game");
        driver.manage().window().fullscreen();

        driver.findElement(By.cssSelector("div.start-button")).click();

        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()=' No Walls']"))).click();

        Actions actions = new Actions(driver);
        Instant lastBallMovedTime = Instant.now();
        int idleTimeThreshold = 10; 

        
        Point lastBallPosition = new Point(0, 0);
        while (true) {
            try {
                
                WebElement ball = driver.findElement(By.xpath("//div[@style='background-color: rgb(236, 100, 75);']"));
                Point ballLocation = ball.getLocation();
                int ballX = ballLocation.getX();
                int ballY = ballLocation.getY();

                
                WebElement snake = driver.findElement(By.xpath("//div[@style='background-color: rgb(51, 110, 123);']"));
                Point snakeLocation = snake.getLocation();
                int snakeX = snakeLocation.getX();
                int snakeY = snakeLocation.getY();

                
                
                if (ballLocation.equals(lastBallPosition)) {
                    if (Duration.between(lastBallMovedTime, Instant.now()).getSeconds() > idleTimeThreshold) {
                        WebElement score = driver.findElement(By.xpath("//h3[@class='score new-best-score']"));
                        System.out.println("Ball hasn't moved. Score: " + score.getText());
                        break; 
                    }
                } else {
                    
                    lastBallMovedTime = Instant.now();
                }

                
                lastBallPosition = ballLocation;

                
                if (ballX > snakeX) {
                    actions.sendKeys(Keys.ARROW_RIGHT).perform(); 
                } else if (ballX < snakeX) {
                    actions.sendKeys(Keys.ARROW_LEFT).perform(); 
                } else if (ballY > snakeY) {
                    actions.sendKeys(Keys.ARROW_DOWN).perform(); 
                } else if (ballY < snakeY) {
                    actions.sendKeys(Keys.ARROW_UP).perform(); 
                }

                
                Thread.sleep(100);
            } catch (NoSuchElementException | StaleElementReferenceException e) {
                System.out.println("Ball not found, Exiting game loop.");
                break; 
            } 
        }
        
        try {
            WebElement finalScore = driver.findElement(By.xpath("//h3[@class='score new-best-score']"));
            System.out.println("Final Score: " + finalScore.getText());
        } catch (NoSuchElementException e) {
            System.out.println("Unable to retrieve final score.");
        }
  driver.quit();
	}
}


