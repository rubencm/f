<script>
    import {goto} from "$app/navigation";
    import md5 from 'md5';

    export let message;

    async function deleteMessage(messageId) {
        const res = await fetch('http://localhost:8080/messages/' + message.id, {
            method: 'DELETE'
        });

        if (res.ok) {
            goto('/');
        }
    }
</script>

<div class="row border m-3">
    <div class="col-3 p-2 text-center border-end">
        <div><img alt="" class="rounded-circle m-2"
                  src="https://www.gravatar.com/avatar/{md5(message.posterName)}?d=wavatar"/>
        </div>
        <div class="fw-bold m-2">{message.posterName}</div>
    </div>
    <div class="col-9">
        <div class="border-bottom p-2 fw-bold d-flex">
            {message.title}
            <a class="ms-auto btn btn-warning btn-sm mx-1" href="/messages/{message.id}/edit">
                <i class="fas fa-pencil-alt text-white"></i>
            </a>
            <button class="btn btn-danger btn-sm mx-1" on:click|once={() => deleteMessage(message.id)} type="button">
                <i class="fas fa-trash text-white"></i>
            </button>
        </div>
        <div class="p-2">
            {@html message.message.replace(/\n/g, "<br />")}
        </div>
        {#if message.numModifications > 0}
            <div class="text-center bg-light border border-secondary my-2 p-2" style="width: 60%; margin: auto">
                <i>
                    Este mensaje ha sido modificado {message.numModifications} veces.
                    Modificado por última vez el {(new Date(Date.parse(message.lastModificationDate))).toDateString()}.
                </i>
            </div>
        {/if}
    </div>
</div>