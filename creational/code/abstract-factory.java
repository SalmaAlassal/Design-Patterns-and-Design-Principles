package creational.code;

interface Chair {
    void hasLegs();
    void sitOn();
}

interface Sofa {
    void hasLegs();
    void sitOn();
}

interface CoffeeTable {
    void hasLegs();
    void sitOn();
}


class ModernChair implements Chair {
    public void hasLegs() {
        System.out.println("Modern Chair has 4 legs");
    }
    public void sitOn() {
        System.out.println("Modern Chair is comfortable");
    }
}

class ModernSofa implements Sofa {
    public void hasLegs() {
        System.out.println("Modern Sofa has 4 legs");
    }
    public void sitOn() {
        System.out.println("Modern Sofa is comfortable");
    }
}

class ModernCoffeeTable implements CoffeeTable {
    public void hasLegs() {
        System.out.println("Modern CoffeeTable has 4 legs");
    }
    public void sitOn() {
        System.out.println("Modern CoffeeTable is comfortable");
    }
}

class VictorianChair implements Chair {
    public void hasLegs() {
        System.out.println("Victorian Chair has 4 legs");
    }
    public void sitOn() {
        System.out.println("Victorian Chair is comfortable");
    }
}

class VictorianSofa implements Sofa {
    public void hasLegs() {
        System.out.println("Victorian Sofa has 4 legs");
    }
    public void sitOn() {
        System.out.println("Victorian Sofa is comfortable");
    }
}

class VictorianCoffeeTable implements CoffeeTable {
    public void hasLegs() {
        System.out.println("Victorian CoffeeTable has 4 legs");
    }

    public void sitOn() {
        System.out.println("Victorian CoffeeTable is comfortable");
    }
}

interface FurnitureFactory {
    Chair createChair();

    Sofa createSofa();

    CoffeeTable createCoffeeTable();
}

class ModernFurnitureFactory implements FurnitureFactory {
    public Chair createChair() {
        return new ModernChair();
    }
    public Sofa createSofa() {
        return new ModernSofa();
    }
    public CoffeeTable createCoffeeTable() {
        return new ModernCoffeeTable();
    }
}

class VictorianFurnitureFactory implements FurnitureFactory {
    public Chair createChair() {
        return new VictorianChair();
    }
    public Sofa createSofa() {
        return new VictorianSofa();
    }
    public CoffeeTable createCoffeeTable() {
        return new VictorianCoffeeTable();
    }
}

class Client {
    public static void main(String[] args) {
        FurnitureFactory modernFactory = new ModernFurnitureFactory();
        FurnitureFactory victorianFactory = new VictorianFurnitureFactory();
        Chair modernChair = modernFactory.createChair();
        Sofa modernSofa = modernFactory.createSofa();
        CoffeeTable modernCoffeeTable = modernFactory.createCoffeeTable();
        Chair victorianChair = victorianFactory.createChair();
        Sofa victorianSofa = victorianFactory.createSofa();
        CoffeeTable victorianCoffeeTable = victorianFactory.createCoffeeTable();
        modernChair.hasLegs();
        modernChair.sitOn();
        modernSofa.hasLegs();
        modernSofa.sitOn();
        modernCoffeeTable.hasLegs();
        modernCoffeeTable.sitOn();
        victorianChair.hasLegs();
        victorianChair.sitOn();
        victorianSofa.hasLegs();
        victorianSofa.sitOn();
        victorianCoffeeTable.hasLegs();
        victorianCoffeeTable.sitOn();
    }
}