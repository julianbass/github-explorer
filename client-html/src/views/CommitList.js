import { View } from "/src/views/View.js";

export class CommitList extends View {


    constructor() {
      super();
    }

    render(commits) {
            const el = document.querySelector(".repo-selected-info");
            el.classList.add("show");

            const html = `<h3>Commits</h3>            
            ${commits
              .map((commit, index) => {
                return `
                <div id="commit-${index}" class="commit-details">
                    <p>Created ON: ${new Date(
                      commit.createdOn
                    ).toDateString()}</p>
                    <p>Author Name: ${commit.author.name}</p>
                    <p>Author Email: ${commit.author.email}</p>
                    <p>Message:  ${commit.message}</p>
                    <button id="show-commit-details" class="btn-simple">Details</button>
                </div>
            `;
              })
              .join("\n")}`;

    el.innerHTML = html;

    this.bindEventListeners();
  }

  bindEventListeners() {}

  update(commits) {
    this.render(commits);
  }

  unmount() {
    document.querySelector(".repo-selected-info").innerHTML = "";
  }
}