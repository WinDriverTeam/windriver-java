package ua.windriver.model.request;

import ua.windriver.model.automation.Condition;
import ua.windriver.model.automation.FindOption;
import ua.windriver.model.automation.SearchScopeOption;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ElementLocationControlRequest {

    @JsonProperty("TreeWalkerType")
    private String treeWalkerType;

    @JsonProperty("SearchScope")
    private SearchScopeOption searchScope;

    @JsonProperty("ParrentWinDriverElementId")
    private String parentWinDriverElementId;

    @JsonProperty("FindOption")
    private FindOption findOption;

    @JsonProperty("PropertyConditionModels")
    private List<Condition> conditionModels;

    public String getTreeWalkerType() {
        return treeWalkerType;
    }

    public void setTreeWalkerType(String treeWalkerType) {
        this.treeWalkerType = treeWalkerType;
    }

    public SearchScopeOption getSearchScope() {
        return searchScope;
    }

    public void setSearchScope(SearchScopeOption searchScope) {
        this.searchScope = searchScope;
    }

    public String getParentWinDriverElementId() {
        return parentWinDriverElementId;
    }

    public void setParentWinDriverElementId(String parentWinDriverElementId) {
        this.parentWinDriverElementId = parentWinDriverElementId;
    }

    public FindOption getFindOption() {
        return findOption;
    }

    public void setFindOption(FindOption findOption) {
        this.findOption = findOption;
    }

    public List<Condition> getConditionModels() {
        return conditionModels;
    }

    public void setConditionModels(List<Condition> conditionModels) {
        this.conditionModels = conditionModels;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        ElementLocationControlRequest that = (ElementLocationControlRequest) o;

        if (treeWalkerType != null ? !treeWalkerType.equals(that.treeWalkerType) : that.treeWalkerType != null)
            return false;
        if (searchScope != null ? !searchScope.equals(that.searchScope) : that.searchScope != null)
            return false;
        if (parentWinDriverElementId != null ?
                !parentWinDriverElementId.equals(that.parentWinDriverElementId) :
                that.parentWinDriverElementId != null)
            return false;
        if (findOption != null ? !findOption.equals(that.findOption) : that.findOption != null)
            return false;
        return conditionModels != null ?
                conditionModels.equals(that.conditionModels) :
                that.conditionModels == null;

    }

    @Override
    public int hashCode() {
        int result = treeWalkerType != null ? treeWalkerType.hashCode() : 0;
        result = 31 * result + (searchScope != null ? searchScope.hashCode() : 0);
        result = 31 * result + (parentWinDriverElementId != null ? parentWinDriverElementId.hashCode() : 0);
        result = 31 * result + (findOption != null ? findOption.hashCode() : 0);
        result = 31 * result + (conditionModels != null ? conditionModels.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ElementLocationControlRequest{" +
                "treeWalkerType='" + treeWalkerType + '\'' +
                ", searchScope='" + searchScope + '\'' +
                ", parentWinDriverElementId='" + parentWinDriverElementId + '\'' +
                ", findOption='" + findOption + '\'' +
                ", conditionModels=" + conditionModels +
                '}';
    }
}
