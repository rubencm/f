<script>
    export let topic;
    import md5 from 'md5';

    let creationDate = new Date(Date.parse(topic.creationDate));
    let lastPostDate = new Date(Date.parse(topic.lastPostDate));
</script>

<tr>
    <td>
        <div class="d-flex align-items-center">
            <i class="fas fa-chevron-circle-right fa-2x text-primary"></i>
            <div class="mx-3">
                <div><a href="/topics/{topic.id}/0">{topic.title}</a></div>
                <small>Por <strong>{topic.author}</strong> el <i>{creationDate.toDateString()}</i><small>
            </div>
        </div>
    </td>
    <td>
        <div class="mx-3 d-flex align-items-center">
            <img alt="" class="rounded-circle"
                 src="https://www.gravatar.com/avatar/{md5(topic.author)}?d=wavatar" style="width: 2rem; height: 2rem"/>
            <div class="mx-3">
                <i>{creationDate.toDateString()}</i><br>
                Por <strong>{topic.author}</strong>
            </div>
        </div>
    </td>
    <td class="text-center">{topic.numMessages}</td>
    <td>
        <div class="mx-3 d-flex align-items-center">
            {#if topic.lastPosterName != ""}
                <img class="rounded-circle" src="https://www.gravatar.com/avatar/{md5(topic.lastPosterName)}?d=wavatar"
                     style="width: 2rem; height: 2rem" alt=""/>
                <div class="mx-3">
                    <i>{lastPostDate.toDateString()}</i><br>
                    Por <strong>{topic.lastPosterName}</strong>
                </div>
            {:else}
                Sin mensajes
            {/if}
        </div>
    </td>
</tr>