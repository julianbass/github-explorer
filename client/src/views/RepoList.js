import { View } from "/src/views/View.js";

export class RepoList extends View {
    constructor(props) {
      super();
        this.onRepoSelect = props.onRepoSelect;
        this.activeRepo = -1;
    }

    render(repos) {
            const el = document.querySelector(".repo-list");

            const html = `${repos
      .map((repo, index) => {
       
        const isActive = index === this.activeRepo;
       
        return `
                <li id="repo-${index}" class="${isActive ? "active-repo" : ""}">
                    <p>${repo.owner}/${repo.repoName}</p>
                </li>
            `;
      })
      .join("\n")}`;

 
    el.innerHTML = html;

    this.bindEventListeners(repos);
  }

  bindEventListeners(repos) {
    repos.forEach((repo, index) => {
      document.querySelector(`#repo-${index}`).addEventListener("click", () => {
        this.onRepoSelect && this.onRepoSelect(repo.owner, repo.repoName);
        this.activeRepo = index;
        this.render(repos);
      });
    });
  }

  update(repos) {
    this.render(repos);
  }
}