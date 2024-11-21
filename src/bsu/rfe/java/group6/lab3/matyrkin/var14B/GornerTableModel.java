package bsu.rfe.java.group6.lab3.matyrkin.var14B;
import javax.swing.table.AbstractTableModel;
@SuppressWarnings("serial")
public class GornerTableModel extends AbstractTableModel {
    private Double[] coefficients;
    private Double from;
    private Double to;
    private Double step;
    // Конструктор для инициализации модели таблицы
    public GornerTableModel(Double from, Double to, Double step,
                            Double[] coefficients) {
        this.from = from;
        this.to = to;
        this.step = step;
        this.coefficients = coefficients;
    }
    public Double getFrom() {
        return from;
    }
    public Double getTo() {
        return to;
    }
    public Double getStep() {
        return step;
    }
    // Количество столбцов в таблице
    public int getColumnCount() {
        return 3;
    }
    // Количество строк в таблице
    public int getRowCount() {
// Вычислить количество точек между началом и концом отрезка
// исходя из шага табулирования
        return (int) Math.ceil((to - from) / step) + 1;
    }
    // Получение значения в конкретной ячейке
    public Object getValueAt(int row, int col) {
        double x = from + step * row;
        if (col == 0) {
            return x;
        } else if (col == 1) {
            Double result = 0.0;
            // Вычисление значения многочлена по схеме Горнера
            for (int i = 0; i < coefficients.length; i++) {
                result = result * x + coefficients[i];
            }
            return result;
        } else if (col == 1) {
            Double result = coefficients[0];
            for (int i = 1; i < coefficients.length; i++) {
                result = result * x + coefficients[i];
            }
            return result;
        }  else {
            Double result = (Double) getValueAt(row, 1);
            String integerPart = String.valueOf(result.intValue());

            // Проверка на палиндром только для неотрицательных целых чисел
            if (result >= 0) {
                return isPalindrome(integerPart);
            } else {
                return false; // Отрицательные числа не являются палиндромами
            }
        }
    }
    //Название столбца
    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "Значение X";
            case 1:
                return "Значение многочлена";
            case 2:
                return "Целая часть палиндром?";
            default:
                return "";
        }
    }
    public Class<?> getColumnClass(int col) {
        if (col == 2) {
            return Boolean.class;
        }
        return Double.class;
    }
    private boolean isPalindrome(String str) {
        int left = 0, right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
