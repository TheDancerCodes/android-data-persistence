package com.thedancercodes.recipe.app.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;

@Entity
public class Recipe
{
    // Allow Room to handle the generation of our IDs for us.
    @PrimaryKey(autoGenerate = true)
    private long id;

    private String name;

    private String description;

    // To have control over the columns used for our database.
    @ColumnInfo(name = "image_resource_id")
    private int imageResourceId;

    // To prevent room from processing our recipe steps.
    @Ignore
    private List<RecipeStep> steps;

    @ColumnInfo(name = "number_of_stars")
    private Integer numberOfStars;

    public Recipe (String name, String description, int imageResourceId)
    {
        this.name = name;
        this.description = description;
        this.imageResourceId = imageResourceId;
    }

    public long getId ()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public int getImageResourceId()
    {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId)
    {
        this.imageResourceId = imageResourceId;
    }

    public List<RecipeStep> getSteps()
    {
        return steps;
    }

    public void setSteps(List<RecipeStep> steps)
    {
        this.steps = steps;
    }

    public Integer getNumberOfStars() {
        return numberOfStars;
    }

    public void setNumberOfStars(Integer numberOfStars) {
        this.numberOfStars = numberOfStars;
    }

    @Override
    public String toString ()
    {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imageResourceId=" + imageResourceId +
                ", steps=" + steps +
                '}';
    }
}
