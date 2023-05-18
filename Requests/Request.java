package Requests;

@SuppressWarnings("unused")
public class Request {
    int page;
    int id;

    public Request(int page, int id) {
        this.page = page;
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Request request = (Request) o;

        return page == request.page;
    }

}
