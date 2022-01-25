import { View } from "/src/views/View.js";

export class AddRepo extends View {

    constructor(props) {
        super();
        this.onRepoAdd = props.onRepoAdd;
        this.showForm = false;
    }

    render() {
        const el = document.querySelector(".add-repo-panel");
        // el.classList.add("show");
        const html = `
        <div>
            <button id="add-repo-btn" class="btn-simple">Add Repo</button>
            <div class="add-repo-form ${this.showForm ? "show" : ""}">
                <form>
                    <label for="owner">Owner</label><br>
                    <input type="text" id="owner" name="owner" value=""><br>
                    <label for="repo-name">Repo Name</label><br>
                    <input type="text" id="repo-name" name="repo-name" value=""><br><br>
                </form>
                <button id="add-repo-submit" class="btn-simple">Submit</button>
            </div>
        </div>
        `;

        el.innerHTML = html;


        this.bindEventListeners();


    }

    bindEventListeners() {
        document.querySelector("#add-repo-submit").addEventListener("click", () => {
            const owner = document.querySelector("#owner").value;
            const repoName = document.querySelector("#repo-name").value;
           
            this.onRepoAdd && this.onRepoAdd(owner, repoName);
            document.querySelector(".add-repo-form").classList.remove("show");
            this.showForm = false;
            this.render();
        });

        document.querySelector("#add-repo-btn").addEventListener("click", () => {
            this.showForm = true;
            this.render();
        })
    }

    update() {
        this.render();
    }
}