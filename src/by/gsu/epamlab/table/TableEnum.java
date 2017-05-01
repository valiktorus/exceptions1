package by.gsu.epamlab.table;

import by.gsu.epamlab.Constants;

public enum TableEnum {
    NAME("Name", "%-16s"), PRICE("Price", "%12s"), NUMBER("Number", "%10s"), DISCOUNT("Discount", "%15s"), COST("Cost", "%12s");

    private String name;
    private String format;

    TableEnum(String name, String format) {
        this.name = name;
        this.format = format;
    }

    public String getName() {
        return name;
    }

    public String getFormat() {
        return format;
    }

    public static String getTableTitle(){
        String format = TableEnum.NAME.getFormat() +
                TableEnum.PRICE.getFormat() +
                TableEnum.NUMBER.getFormat() +
                TableEnum.DISCOUNT.getFormat() +
                TableEnum.COST.getFormat() +
                Constants.NEW_LINE;
        return String.format(format, NAME, PRICE, NUMBER, DISCOUNT, COST);
    }

    @Override
    public String toString() {
        return name;
    }
}
