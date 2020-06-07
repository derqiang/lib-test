package me.daqiang.utils.json;

import lombok.Data;

import java.util.List;

/**
 * @ClassName MedicineSuggestions
 * @Description classdes
 * @Author daqiang
 * @Date 2020/3/17 1:31 下午
 * @Version 1.0
 **/
public class MedicineSuggestions {

    private String category;
    private List<MedicineSuggestionsContent> content;

    public MedicineSuggestions(String category, List<MedicineSuggestionsContent> medicineSuggestions) {
        this.category = category;
        this.content = medicineSuggestions;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<MedicineSuggestionsContent> getContent() {
        return content;
    }

    public void setContent(List<MedicineSuggestionsContent> content) {
        this.content = content;
    }
}
