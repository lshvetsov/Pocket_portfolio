package ru.redflag.pocketPortfolio.data.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import ru.redflag.pocketPortfolio.data.enums.Broker;
import ru.redflag.pocketPortfolio.data.enums.Status;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@Data
public class Portfolio {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    private String name;
    private LocalDate horizon;
    @Enumerated(EnumType.STRING)
    private Broker broker;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.ACTIVE;

    @OneToMany(mappedBy = "portfolio")
    private List<Position> positions = new ArrayList<>();

    public Portfolio() {

    }
}
