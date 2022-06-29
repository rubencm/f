<script>
    import {goto} from '$app/navigation';

    export let forumId;
    export let topicId;
    export let message = {};

    async function onSubmit() {

        const res = await fetch('http://localhost:8080/messages/' + (message.id ?? ''), {
            method: message.id ? 'PUT' : 'POST',
            body: JSON.stringify({
                messageTitle: message.title,
                messageBody: message.message,
                posterName: message.posterName,
                topicId: topicId,
                forumId: forumId
            }),
            headers: {
                "Content-type": "application/json"
            }
        });

        if (res.ok) {
            goto('/');
        }
    }
</script>


<form on:submit|preventDefault={onSubmit}>
    {#if !message.id}
        <div class="form-group m-2">
            <label for="username">Nombre de usuario</label>
            <input type="text" class="form-control" id="username" aria-describedby="username"
                   bind:value={message.posterName}>
        </div>
    {/if}
    <div class="form-group m-2">
        <label for="title">Título</label>
        <input bind:value={message.title} class="form-control" id="title" type="text">
    </div>
    <div class="form-group m-2">
        <label for="message">Mensaje</label>
        <textarea bind:value={message.message} class="form-control" id="message" rows="5"/>
    </div>
    <button class="btn btn-primary m-2" type="submit">Submit</button>
</form>