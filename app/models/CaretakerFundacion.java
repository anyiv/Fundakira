package models;

import javax.inject.*;

@Singleton
public class CaretakerFundacion{
    
    private MementoFundacion memento;

    public CaretakerFundacion() {

    }

    public CaretakerFundacion(MementoFundacion memento) {
        this.memento = memento;
    }

    public MementoFundacion getMemento() {
        return this.memento;
    }

    public void setMemento(MementoFundacion memento) {
        this.memento = memento;
    }
}