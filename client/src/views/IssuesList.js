import { View } from "/src/views/View.js";

export class IssuesList extends View {

    constructor() {
      super();
    }

    render(issues) {
            const el = document.querySelector(".repo-selected-info");
            el.classList.add("show");

            const html = `<h3>Issues</h3>            
            ${issues
              .map((issue, index) => {
                return `
                <div id="issues-${index}" class="issues-details">
                    <p>Title: ${issue.title} </p>
                    <p>State: ${issue.state}</p>
                    <p>User: ${issue.user}</p>
                    <p>Body:  ${issue.body}</p>
                    <p>Created At:  ${new Date(
                      issue.createdAt
                    ).toDateString()}</p>
                </div>
            `;
              })
              .join("\n")}`;

    el.innerHTML = html;

    this.bindEventListeners();
  }

  bindEventListeners() {}

  update(issues) {
    this.render(issues);
  }
}