const URL = 'http://localhost:8080/api';
const FORM_MODES = { NEW: 'new', EDIT: 'edit' };
let formMode = FORM_MODES.NEW;
let categoryForm;
let categories = [];
 
const saveCategory = (e) => {
  e.preventDefault();
  const formData = new FormData(e.target);
  const category = {};
  category['name'] = formData.get('name');
  category['description'] = formData.get('description')

 
  if (formMode === FORM_MODES.NEW) {
    fetch(`${URL}/category`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(category)
    }).then((result) => {
      result.json().then((category) => {
        categories.push(category);
        renderCategories();
      });
    });
  } else if(formMode === FORM_MODES.EDIT) {
    fetch(`${URL}/category/${formData.get('id')}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(category)
    }).then((result) => {
      result.json().then((category) => {
        categories = categories.map((c) => c.id === category.id ? category : c);
        renderCategories();
      });
    });
  };
 
  formMode = FORM_MODES.NEW;
  e.target.reset();
};
 
const deleteCategory = (id) => {
  fetch(`${URL}/category/${id}`, {
    method: 'DELETE',
    headers: {
      'Content-Type': 'application/json'
    },
  }).then(() => {
    categories = categories.filter((category) => category.id !== id);
    renderCategories();
  });
}
 
const editCategory = (id) => {
  formMode = FORM_MODES.EDIT;
  const category = categories.find((category) => category.id === id);
  categoryForm.querySelector('input[name=id]').value = category.id;
  categoryForm.querySelector('input[name=name]').value = category.name;
  categoryForm.querySelector('input[name=description').value = category.description;
};
 
const indexCategories = () => {
  fetch(`${URL}/category`, {
    method: 'GET'
  }).then((result) => {
    result.json().then((result) => {
      categories = result;
      renderCategories();
    });
  });
  renderCategories();
};
 
const createCell = (text) => {
  const cell = document.createElement('td');
  cell.innerText = text;
  return cell;
};
 
const createActionsCell = (id) => {
  const cell = document.createElement('td');
  const deleteButton = document.createElement('button');
  deleteButton.innerText = "Delete";
  deleteButton.addEventListener('click', () => {
    deleteCategory(id);
  });
  cell.appendChild(deleteButton);
  const editButton = document.createElement('button');
  editButton.innerText = "Edit";
  editButton.addEventListener('click', () => {
    editCategory(id);
  });
  cell.appendChild(editButton);
  return cell;
}
 
const renderCategories = () => {
  const display = document.querySelector('#categoryDisplay');
  display.innerHTML = '';
  categories.forEach((category) => {
    const row = document.createElement('tr');
    row.appendChild(createCell(category.id));
    row.appendChild(createCell(category.name));
    row.appendChild(createCell(category.description));
    row.appendChild(createActionsCell(category.id));
    display.appendChild(row);
  });
};
 
document.addEventListener('DOMContentLoaded', function () {
  categoryForm = document.querySelector('#categoryForm');
  categoryForm.addEventListener('submit', saveCategory);
  indexCategories();
});