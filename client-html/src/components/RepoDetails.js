export const COMMIT = 0;
export const ISSUES = 1;
export const PULLS = 2;

export class RepoDetails {
    constructor(props) {
        this.onRequestToggle = props.onRequestToggle;
    }

    getHealthIconBgColor(percentage) {
        let backgroundColor = "red";
        if (percentage > 70) {
            backgroundColor = "green";
        } else if (percentage > 30) {
            backgroundColor = "orange";
        }

        return backgroundColor;
    }

    render(repo) {
        document.querySelector(".repo-panel").classList.add("show");
        const el = document.querySelector("#repo-details");
        el.classList.add("show");

        const backgroundColor = this.getHealthIconBgColor(repo.healthPercentage);

        const html = `
        <h2 id="repo-name">${repo.owner}/${repo.name}</h3>
        <div class="health-icon-wrapper" style="background-color: ${backgroundColor};">
            <p class="health-label">Health: ${repo.healthPercentage}</p>            
        </div>
        <p>Github Page: <a href="https://github.com/${repo.owner}/${repo.name}">https://github.com/${repo.owner}/${repo.name}</a></p>
        <button id="show-commits" class="btn-simple">Commits</button>
        <button id="show-issues" class="btn-simple">Issues</button>
        <button id="show-pulls" class="btn-simple">Pulls</button>        
        `;

        el.innerHTML = html;

        this.bindEventListeners();
    }

    bindEventListeners() {
        document
            .querySelector("#show-commits")
            .addEventListener(
                "click",
                () => this.onRequestToggle && this.onRequestToggle(COMMIT)
            );
        document
            .querySelector("#show-issues")
            .addEventListener(
                "click",
                () => this.onRequestToggle && this.onRequestToggle(ISSUES)
            );
        document
            .querySelector("#show-pulls")
            .addEventListener(
                "click",
                () => this.onRequestToggle && this.onRequestToggle(PULLS)
            );
    }

    update(repo) {
        this.render(repo);
    }
}