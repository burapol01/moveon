<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script>
  document.addEventListener('DOMContentLoaded', function () {
    var body = document.body;
    var toggle = document.querySelector('.app-nav-toggle');
    var backdrop = document.querySelector('.nav-backdrop');
    var navLinks = document.querySelectorAll('.sidenav a');

    function closeNav() {
      body.classList.remove('nav-open');
    }

    function toggleNav() {
      body.classList.toggle('nav-open');
    }

    function enhanceTables() {
      var wrappers = document.querySelectorAll('.table-responsive');

      wrappers.forEach(function (wrapper, wrapperIndex) {
        if (wrapper.dataset.paginationReady === 'true') {
          return;
        }

        var table = wrapper.querySelector('table');
        var tbody = table ? table.querySelector('tbody') : null;
        if (!table || !tbody) {
          return;
        }

        var rows = Array.prototype.slice.call(tbody.querySelectorAll('tr'));
        if (!rows.length) {
          return;
        }

        var isEmptyState = rows.length === 1 && rows[0].children.length === 1 && rows[0].children[0].hasAttribute('colspan');
        wrapper.classList.add('is-enhanced');
        wrapper.dataset.paginationReady = 'true';

        if (isEmptyState) {
          return;
        }

        var pageSize = parseInt(wrapper.dataset.pageSize || '', 10);
        if (!pageSize || Number.isNaN(pageSize)) {
          pageSize = window.innerWidth < 768 ? 6 : 8;
        }

        var totalRows = rows.length;
        var totalPages = Math.ceil(totalRows / pageSize);
        if (totalPages <= 1) {
          return;
        }

        var pagination = document.createElement('div');
        pagination.className = 'table-pagination';
        pagination.innerHTML = '<div class="table-pagination__meta"></div><div class="table-pagination__pages"></div>';
        wrapper.insertAdjacentElement('afterend', pagination);

        var meta = pagination.querySelector('.table-pagination__meta');
        var pages = pagination.querySelector('.table-pagination__pages');
        var currentPage = 1;

        function renderPage(page) {
          currentPage = page;
          var start = (page - 1) * pageSize;
          var end = start + pageSize;

          rows.forEach(function (row, index) {
            row.style.display = index >= start && index < end ? '' : 'none';
          });

          meta.textContent = 'Rows ' + (start + 1) + '-' + Math.min(end, totalRows) + ' of ' + totalRows;
          pages.innerHTML = '';

          function addButton(label, targetPage, options) {
            var button = document.createElement('button');
            button.type = 'button';
            button.className = 'table-pagination__button';
            button.textContent = label;
            if (options && options.active) {
              button.classList.add('is-active');
            }
            if (options && options.disabled) {
              button.disabled = true;
            }
            button.addEventListener('click', function () {
              if (!button.disabled && targetPage !== currentPage) {
                renderPage(targetPage);
                wrapper.scrollTop = 0;
              }
            });
            pages.appendChild(button);
          }

          addButton('<', currentPage - 1, { disabled: currentPage === 1 });

          for (var i = 1; i <= totalPages; i += 1) {
            addButton(String(i), i, { active: i === currentPage });
          }

          addButton('>', currentPage + 1, { disabled: currentPage === totalPages });
        }

        pagination.dataset.tableIndex = String(wrapperIndex);
        renderPage(1);
      });
    }

    if (toggle) {
      toggle.addEventListener('click', toggleNav);
    }

    if (backdrop) {
      backdrop.addEventListener('click', closeNav);
    }

    navLinks.forEach(function (link) {
      link.addEventListener('click', closeNav);
    });

    document.addEventListener('keydown', function (event) {
      if (event.key === 'Escape') {
        closeNav();
      }
    });

    window.addEventListener('resize', function () {
      if (window.innerWidth >= 992) {
        closeNav();
      }
    });

    enhanceTables();
  });
</script>
</body>

</html>

