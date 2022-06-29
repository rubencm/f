<script>
    export let pag;
    export let numElements;

    let numPages = parseInt(((numElements-1) / 10) + 1);
</script>

<div class="d-flex">
    <div class="">
        Mostrando  {pag * 10}-{Math.min(pag * 10 + 10, numElements)} de {numElements}
    </div>

    <nav aria-label="Page navigation example" class="ms-auto">
        <ul class="pagination">
            {#if pag > 0}
                <li class="page-item"><a class="page-link" href="{pag-1}">Anterior</a></li>
            {/if}
            {#each Array(numPages) as _, i}
                <li class="page-item{i == pag ? ' active' : ''}"><a class="page-link" href="{i}">{i + 1}</a></li>
            {/each}
            {#if pag + 1 < numPages}
                <li class="page-item"><a class="page-link" href="{pag+1}">Siguiente</a></li>
            {/if}
        </ul>
    </nav>
</div>
