package greendao;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here

import com.google.gson.annotations.SerializedName;
// KEEP INCLUDES END
/**
 * Entity mapped to table "PAGINATION".
 */
public class Pagination {

    // KEEP FIELDS - put your custom fields here

    private Long id;
    @SerializedName("total_results")
    private Long total;
    @SerializedName("results_per_page")
    private Long perPage;
    @SerializedName("num_pages")
    private Long numPages;
    @SerializedName("current_page")
    private Long currentPage;
    @SerializedName("first_on_page")
    private Long firstOnPage;
    @SerializedName("last_on_page")
    private Long lastOnPage;

    // KEEP FIELDS END

    public Pagination() {
    }

    public Pagination(Long id) {
        this.id = id;
    }

    public Pagination(Long id, Long total, Long perPage, Long numPages, Long currentPage, Long firstOnPage, Long lastOnPage) {
        this.id = id;
        this.total = total;
        this.perPage = perPage;
        this.numPages = numPages;
        this.currentPage = currentPage;
        this.firstOnPage = firstOnPage;
        this.lastOnPage = lastOnPage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getPerPage() {
        return perPage;
    }

    public void setPerPage(Long perPage) {
        this.perPage = perPage;
    }

    public Long getNumPages() {
        return numPages;
    }

    public void setNumPages(Long numPages) {
        this.numPages = numPages;
    }

    public Long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Long currentPage) {
        this.currentPage = currentPage;
    }

    public Long getFirstOnPage() {
        return firstOnPage;
    }

    public void setFirstOnPage(Long firstOnPage) {
        this.firstOnPage = firstOnPage;
    }

    public Long getLastOnPage() {
        return lastOnPage;
    }

    public void setLastOnPage(Long lastOnPage) {
        this.lastOnPage = lastOnPage;
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}
