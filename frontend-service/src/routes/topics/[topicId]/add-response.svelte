<script context="module">
    export async function load({page, fetch}) {
        const {topicId} = page.params;
        const topic = await fetch('http://localhost:8080/topics/' + topicId).then((r) => r.json());

        return {
            props: {
                topic
            }
        };
    }
</script>

<script>
    import MessageForm from "$lib/MessageForm.svelte";

    export let topic;

</script>


<div class="bg-white shadow p-4">
    <div class="d-flex">
        <h4><a href="/"><i class="fas fa-home"></i></a> &#8594; Responder tema</h4>
    </div>
    <MessageForm forumId={topic.forumId} message={{title: 'RE: ' + topic.title}} topicId={topic.id}/>
</div>