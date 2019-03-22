package sheepyourhack.delf.sheepyourhack_smoczek.dataBase;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.media.Image;
import android.support.annotation.NonNull;


@Entity(tableName = "offers")
public class Offer {

    @PrimaryKey
    @NonNull
    private String title;

    @NonNull
    private String description;

    private Integer price;
//    private Image mainImage;


    public Offer(@NonNull String title, @NonNull String description, int price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public Integer getPrice() {
        return price;
    }
}


