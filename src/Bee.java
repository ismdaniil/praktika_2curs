import java.awt.*;

public class Bee extends Insect {
    Color beeColor = new Color(255, 255, 0);    // Цвет пчелы
    Color deadBeeColor = new Color(100, 100, 100);  // Цвет мертвой пчелы
    Color fedBeeColor = new Color(255, 0, 0);  // Цвет сытой пчелы

    int feedingTime; // Переменная для хранения времени, которое пчела тратит на питие
    int lifeStrength;  // Переменная для хранения жизненной силы пчелы

    public Bee(int positionX, int positionY) {
        super(positionX, positionY);
        feedingTime = 0;
        lifeStrength = Hive.MAX_LIFE_STRENGTH;
    }

    @Override
    public void draw(Graphics g, int cellWidth, int cellHeight, int offsetX, int offsetY) {
        int x = cell.getX() * cellWidth + offsetX;
        int y = cell.getY() * cellHeight + offsetY;

        if (lifeStrength < Hive.MIN_LIFE_STRENGTH) {
            g.setColor(deadBeeColor);
        } else {
            if (lifeStrength > (Hive.MAX_LIFE_STRENGTH - Hive.LIFE_STRENGTH_DELTA)) {
                g.setColor(fedBeeColor);
            } else {
                g.setColor(beeColor);
            }
        }
        g.fillRect(x, y, cellWidth, cellHeight);
    }

    @Override
    public void changeState() {
        Hive hive = Hive.getInstance(); // Получаем экземпляр синглтона Улья

        int oldPositionX = cell.getX();
        int oldPositionY = cell.getY();

        int attempts = 0;
        int direction = 0;

        if (lifeStrength >= Hive.MIN_LIFE_STRENGTH) {
            // Проверяем наличие еды
            if ((oldPositionX + 1) < Hive.N) {
                if (hive.grid[oldPositionX + 1][oldPositionY].food != null) {
                    if (hive.grid[oldPositionX + 1][oldPositionY].food.feeding == null) {
                        hive.grid[oldPositionX + 1][oldPositionY].food.feeding = this;
                        // Пчела пьет 10 тактов
                        if (feedingTime > 10) {
                            feedingTime = 0;
                            hive.grid[oldPositionX + 1][oldPositionY].setFood(null);
                            hive.grid[oldPositionX + 1][oldPositionY].redrawFlag = true;

                            // Увеличиваем жизненную силу, максимизируем, если превышает максимум
                            if ((lifeStrength + Hive.LIFE_STRENGTH_DELTA) > Hive.MAX_LIFE_STRENGTH) {
                                lifeStrength = Hive.MAX_LIFE_STRENGTH;
                            } else {
                                lifeStrength += Hive.LIFE_STRENGTH_DELTA;
                            }
                        } else {
                            direction = Hive.Direction.STAY;
                            feedingTime++;
                        }
                    } else {
                        if (hive.grid[oldPositionX + 1][oldPositionY].food.feeding == this) {
                            if (feedingTime > 10) {
                                feedingTime = 0;
                                hive.grid[oldPositionX + 1][oldPositionY].setFood(null);
                                hive.grid[oldPositionX + 1][oldPositionY].redrawFlag = true;

                                // Увеличиваем жизненную силу, максимизируем, если превышает максимум
                                if ((lifeStrength + Hive.LIFE_STRENGTH_DELTA) > Hive.MAX_LIFE_STRENGTH) {
                                    lifeStrength = Hive.MAX_LIFE_STRENGTH;
                                } else {
                                    lifeStrength += Hive.LIFE_STRENGTH_DELTA;
                                }
                            } else {
                                direction = Hive.Direction.STAY;
                                feedingTime++;
                            }
                        } else {
                            // Ничего не делаем
                        }
                    }
                }
            }

            if ((oldPositionY + 1) < Hive.N) {
                if (hive.grid[oldPositionX][oldPositionY + 1].food != null) {
                    if (hive.grid[oldPositionX][oldPositionY + 1].food.feeding == null) {
                        hive.grid[oldPositionX][oldPositionY + 1].food.feeding = this;
                        // Пчела пьет 10 тактов
                        if (feedingTime > 10) {
                            feedingTime = 0;
                            hive.grid[oldPositionX][oldPositionY + 1].setFood(null);
                            hive.grid[oldPositionX][oldPositionY + 1].redrawFlag = true;

                            // Увеличиваем жизненную силу, максимизируем, если превышает максимум
                            if ((lifeStrength + Hive.LIFE_STRENGTH_DELTA) > Hive.MAX_LIFE_STRENGTH) {
                                lifeStrength = Hive.MAX_LIFE_STRENGTH;
                            } else {
                                lifeStrength += Hive.LIFE_STRENGTH_DELTA;
                            }
                        } else {
                            direction = Hive.Direction.STAY;
                            feedingTime++;
                        }
                    } else {
                        if (hive.grid[oldPositionX][oldPositionY + 1].food.feeding == this) {
                            if (feedingTime > 10) {
                                feedingTime = 0;
                                hive.grid[oldPositionX][oldPositionY + 1].setFood(null);
                                hive.grid[oldPositionX][oldPositionY + 1].redrawFlag = true;

                                // Увеличиваем жизненную силу, максимизируем, если превышает максимум
                                if ((lifeStrength + Hive.LIFE_STRENGTH_DELTA) > Hive.MAX_LIFE_STRENGTH) {
                                    lifeStrength = Hive.MAX_LIFE_STRENGTH;
                                } else {
                                    lifeStrength += Hive.LIFE_STRENGTH_DELTA;
                                }
                            } else {
                                direction = Hive.Direction.STAY;
                                feedingTime++;
                            }
                        } else {
                            // Ничего не делаем
                        }
                    }
                }
            }
        }
    }
}