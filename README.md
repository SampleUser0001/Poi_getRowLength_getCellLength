# Poi_getRowLength_getCellLength

PoiでExcelの行の数、列の数を取得する。

## 行数取得

``` java
public int getRowLength(String sheetName) {
    this.sheetOpen(sheetName);
    return this.sheet.getPhysicalNumberOfRows();
}

```

## 列数取得

``` java
public int getColumnLength(String sheetName) {
    this.sheetOpen(sheetName);
    return this.sheet.getRow(0).getPhysicalNumberOfCells();
}
```

## 実行

``` bash
mvn clean compile exec:java -Dexec.mainClass="ittimfn.sample.poi.App" -Dexec.args="$(pwd)/sample.xlsx Sheet1"
```
