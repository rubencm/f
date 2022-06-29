<script>

    export let forum;
</script>
<div class="col-6 px-2">
    <div class="p-3 border border-2 shadow bg-white">
        <div class="row">
            <div class="col-2">
                <i class="{forum.icon} fa-5x text-primary"></i>
            </div>

            <div class="col-7 border-end border-2 px-3">
                <div class="fs-4"><a class="" href="/forums/{forum.id}/0">{forum.name}</a></div>
                {forum.description}
            </div>

            <div class="col-3 text-center">
                <div class="fs-1">{forum.numTopics}</div>
                <div class="fs-4">temas</div>
            </div>
        </div>
    </div>
</div>